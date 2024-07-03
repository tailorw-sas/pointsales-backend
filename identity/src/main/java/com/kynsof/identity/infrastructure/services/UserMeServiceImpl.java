package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.users.userMe.BusinessPermissionResponse;
import com.kynsof.identity.application.query.users.userMe.UserMeResponse;
import com.kynsof.identity.domain.interfaces.service.IUserMeService;
import com.kynsof.identity.infrastructure.identity.UserPermissionBusiness;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repository.query.BusinessModuleReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserPermissionBusinessReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserSystemReadDataJPARepository;
import com.kynsof.share.core.domain.EUserType;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserMeServiceImpl implements IUserMeService {

    private final UserPermissionBusinessReadDataJPARepository userPermissionBusinessReadDataJPARepository;
    private final UserSystemReadDataJPARepository repositoryQuery;
    private final BusinessModuleReadDataJPARepository businessModuleReadDataJPARepository;

    public UserMeServiceImpl(UserPermissionBusinessReadDataJPARepository userPermissionBusinessReadDataJPARepository,
                             UserSystemReadDataJPARepository repositoryQuery, BusinessModuleReadDataJPARepository businessModuleReadDataJPARepository) {
        this.userPermissionBusinessReadDataJPARepository = userPermissionBusinessReadDataJPARepository;
        this.repositoryQuery = repositoryQuery;
        this.businessModuleReadDataJPARepository = businessModuleReadDataJPARepository;
    }

    @Override
   // @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null", key = "#userId")
    public UserMeResponse getUserInfo(UUID userId) {
        var userSystem = repositoryQuery.findByKeyCloakIdl(userId)
                .orElseThrow(() -> new BusinessNotFoundException(new GlobalBusinessException(
                        DomainErrorMessage.USER_NOT_FOUND, new ErrorField("id", "User not found."))));

        if (userSystem.getUserType().equals(EUserType.SUPER_ADMIN)) {
            List<BusinessPermissionResponse> businessPermissionResponses =getAllBusinessesWithPermissions();
            return createUserMeResponse(userSystem,businessPermissionResponses );
        }

        var userPermissions = userPermissionBusinessReadDataJPARepository.findUserPermissionBusinessByUserId(userId);
        var businessResponses = groupUserPermissionsByBusiness(userPermissions);

        return createUserMeResponse(userSystem,    new ArrayList<>(businessResponses.values()));
    }

    private Map<UUID, BusinessPermissionResponse> groupUserPermissionsByBusiness(List<UserPermissionBusiness> userPermissions) {
        return userPermissions.stream()
                .collect(Collectors.groupingBy(upb -> upb.getBusiness().getId()))
                .values().stream()
                .map(userPermissionBusinesses -> {
                    var permissions = userPermissionBusinesses.stream()
                            .map(upb -> upb.getPermission().getCode())
                            .distinct()
                            .toList();
                    var business = userPermissionBusinesses.get(0).getBusiness();

                    return new BusinessPermissionResponse(
                            business.getId(),
                            business.getName(),
                            permissions);
                })
                .collect(Collectors.toMap(BusinessPermissionResponse::getBusinessId, bpr -> bpr));
    }

    private UserMeResponse createUserMeResponse(UserSystem userSystem, List<BusinessPermissionResponse> businessResponses) {
        return new UserMeResponse(
                userSystem.getId(),
                userSystem.getUserName(),
                userSystem.getEmail(),
                userSystem.getName(),
                userSystem.getLastName(),
                userSystem.getImage(),
                userSystem.getSelectedBusiness(),
                businessResponses

        );
    }

    public List<BusinessPermissionResponse> getAllBusinessesWithPermissions() {
        List<Object[]> results = businessModuleReadDataJPARepository.findAllBusinessesWithPermissions();
        Map<UUID, BusinessPermissionResponse> responseMap = new HashMap<>();

        for (Object[] result : results) {
            UUID businessId = (UUID) result[0];
            String businessName = (String) result[1];
            String permissionCode = (String) result[2];

            responseMap.computeIfAbsent(businessId, id -> new BusinessPermissionResponse(id, businessName, new ArrayList<>()))
                    .getPermissions().add(permissionCode);
        }

        return new ArrayList<>(responseMap.values());

    }
}