package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.userrolbusiness.getbyid.UserRoleBusinessResponse;
import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.identity.domain.rules.UserRoleBusinessMustBeUniqueRule;
import com.kynsof.identity.infrastructure.identity.UserPermissionBusiness;
import com.kynsof.identity.infrastructure.repository.command.UserRoleBusinessWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserRoleBusinessReadDataJPARepository;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleBusinessServiceImpl implements IUserRoleBusinessService {

    @Autowired
    private UserRoleBusinessWriteDataJPARepository commandRepository;

    @Autowired
    private UserRoleBusinessReadDataJPARepository queryRepository;

    @Override
    @Transactional
    public void create(List<UserRoleBusinessDto> userRoleBusiness) {
        List<UserPermissionBusiness> saveUserRoleBusinesses = new ArrayList<>();
        for (UserRoleBusinessDto userRoleBusines : userRoleBusiness) {
            RulesChecker.checkRule(new UserRoleBusinessMustBeUniqueRule(this, userRoleBusines));

            saveUserRoleBusinesses.add(new UserPermissionBusiness(userRoleBusines));
        }

        this.commandRepository.saveAll(saveUserRoleBusinesses);
    }

    @Override
    public void update(List<UserRoleBusinessDto> userRoleBusiness) {
        List<UserPermissionBusiness> userRoleBusinesses = userRoleBusiness.stream()
                .map(UserPermissionBusiness::new)
                .collect(Collectors.toList());

        this.commandRepository.saveAll(userRoleBusinesses);
    }

    @Override
    public void delete(UUID id) {
        UserRoleBusinessDto delete = this.findById(id);
        delete.setDeleted(true);
        this.commandRepository.save(new UserPermissionBusiness(delete));
    }

    @Override
    public void delete(List<UserRoleBusinessDto> userRoleBusiness) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserRoleBusinessDto findById(UUID id) {
        Optional<UserPermissionBusiness> object = this.queryRepository.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.USER_ROLE_BUSINESS_NOT_FOUND, "UserPermissionBusiness not found.");
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<UserPermissionBusiness> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<UserPermissionBusiness> data = this.queryRepository.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<UserPermissionBusiness> data) {
        List<UserRoleBusinessResponse> patients = new ArrayList<>();
        for (UserPermissionBusiness o : data.getContent()) {
            patients.add(new UserRoleBusinessResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
