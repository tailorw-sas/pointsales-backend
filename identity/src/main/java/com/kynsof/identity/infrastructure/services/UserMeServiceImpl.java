package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.users.userMe.BusinessPermissionResponse;
import com.kynsof.identity.application.query.users.userMe.UserMeResponse;
import com.kynsof.identity.domain.interfaces.service.IUserMeService;
import com.kynsof.identity.infrastructure.identity.Business;
import com.kynsof.identity.infrastructure.identity.UserPermissionBusiness;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repository.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserMeServiceImpl implements IUserMeService {


    private final UserPermissionBusinessReadDataJPARepository  userPermissionBusinessReadDataJPARepository;

    public UserMeServiceImpl(UserPermissionBusinessReadDataJPARepository userPermissionBusinessReadDataJPARepository) {
        this.userPermissionBusinessReadDataJPARepository = userPermissionBusinessReadDataJPARepository;
    }

    @Override
    public UserMeResponse getUserInfo(UUID userId) {

        List<UserPermissionBusiness> userPermissions = this.userPermissionBusinessReadDataJPARepository.findUserPermissionBusinessByUserId(userId);

        Map<UUID, BusinessPermissionResponse> businessResponses = userPermissions.stream()
                .collect(Collectors.groupingBy(upb -> upb.getBusiness().getId()))
                .values().stream()
                .map(userPermissionBusinesses -> {
                    List<String> permissions = userPermissionBusinesses.stream()
                            .map(upb -> upb.getPermission().getCode())
                            .distinct()
                            .collect(Collectors.toList());
                    Business business = userPermissionBusinesses.get(0).getBusiness();

                    return new BusinessPermissionResponse(
                            business.getId(),
                            business.getName(),
                            permissions);
                })
                .collect(Collectors.toMap(BusinessPermissionResponse::getBusinessId, bpr -> bpr));
        UserMeResponse userMeResponse = getUserMeResponse(userPermissions, businessResponses);
        return userMeResponse;
    }

    private static UserMeResponse getUserMeResponse(List<UserPermissionBusiness> userPermissions, Map<UUID, BusinessPermissionResponse> businessResponses) {
        UserSystem userSystem = userPermissions.get(0).getUser();
        UserMeResponse userMeResponse = new UserMeResponse(
                userSystem.getId(),
                userSystem.getUserName(),
                userSystem.getEmail(),
                userSystem.getName(),
                userSystem.getLastName(),
                userSystem.getImage(),
                userSystem.getSelectedBusiness(),
                new ArrayList<>(businessResponses.values()) // Convertimos el mapa a una lista
        );
        return userMeResponse;
    }
}
