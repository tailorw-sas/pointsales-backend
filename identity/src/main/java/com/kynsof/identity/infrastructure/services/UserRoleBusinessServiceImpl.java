package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.UserRoleBusinessDto;
import com.kynsof.identity.domain.interfaces.service.IUserRoleBusinessService;
import com.kynsof.identity.domain.rules.UserRoleBusinessMustBeUniqueRule;
import com.kynsof.identity.infrastructure.identity.UserRoleBusiness;
import com.kynsof.identity.infrastructure.repository.command.UserRoleBusinessWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserRoleBusinessReadDataJPARepository;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<UserRoleBusiness> saveUserRoleBusinesses = new ArrayList<>();
        for (UserRoleBusinessDto userRoleBusines : userRoleBusiness) {
            RulesChecker.checkRule(new UserRoleBusinessMustBeUniqueRule(this, userRoleBusines));

            saveUserRoleBusinesses.add(new UserRoleBusiness(userRoleBusines));
        }

        this.commandRepository.saveAll(saveUserRoleBusinesses);
    }

    @Override
    public Long countByUserIdAndRoleIdAndBusinessId(UserRoleBusinessDto userRoleBusinessDto) {
        return this.queryRepository.countByUserIdAndRoleIdAndBusinessId(
                userRoleBusinessDto.getUser().getId(), 
                userRoleBusinessDto.getRole().getId(), 
                userRoleBusinessDto.getBusiness().getId()
        );
    }

    @Override
    public void update(List<UserRoleBusinessDto> userRoleBusiness) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(List<UserRoleBusinessDto> userRoleBusiness) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserRoleBusinessDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
