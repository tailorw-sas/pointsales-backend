package com.kynsoft.rrhh.infrastructure.services;

import com.kynsoft.rrhh.domain.dto.UserBusinessRelationDto;
import com.kynsoft.rrhh.domain.interfaces.services.IUserBusinessRelationService;
import com.kynsoft.rrhh.infrastructure.identity.UserBusinessRelation;
import com.kynsoft.rrhh.infrastructure.repository.command.UserBusinessRelationWriteDataJPARepository;
import com.kynsoft.rrhh.infrastructure.repository.query.UserBusinessRelationReadDataJPARepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserBusinessRelationServiceImpl implements IUserBusinessRelationService {

    private final UserBusinessRelationWriteDataJPARepository repositoryCommand;

    private final UserBusinessRelationReadDataJPARepository repositoryQuery;

    public UserBusinessRelationServiceImpl(UserBusinessRelationWriteDataJPARepository repositoryCommand, UserBusinessRelationReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

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
