package com.kynsoft.rrhh.infrastructure.services;

import com.kynsoft.rrhh.domain.dto.UserBusinessRelationDto;
import com.kynsoft.rrhh.domain.interfaces.services.IUserBusinessRelationService;
import com.kynsoft.rrhh.infrastructure.identity.UserBusinessRelation;
import com.kynsoft.rrhh.infrastructure.repository.query.UserBusinessRelationReadDataJPARepository;
import com.kynsoft.rrhh.infrastructure.repository.command.UserBusinessRelationWriteDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserBusinessRelationServiceImpl implements IUserBusinessRelationService {

    @Autowired
    private UserBusinessRelationWriteDataJPARepository repositoryCommand;

    @Autowired
    private UserBusinessRelationReadDataJPARepository repositoryQuery;

    @Override
    public void create(UserBusinessRelationDto object) {
        this.repositoryCommand.save(new UserBusinessRelation(object));
    }

    @Override
    public void update(UserBusinessRelationDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserBusinessRelationDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
