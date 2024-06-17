package com.kynsoft.rrhh.domain.interfaces.services;

import com.kynsoft.rrhh.domain.dto.UserBusinessRelationDto;

import java.util.UUID;

public interface IUserBusinessRelationService {
    public void create(UserBusinessRelationDto object);
    public void update(UserBusinessRelationDto object);
    public void delete(UUID id);
    public UserBusinessRelationDto findById(UUID id);
}