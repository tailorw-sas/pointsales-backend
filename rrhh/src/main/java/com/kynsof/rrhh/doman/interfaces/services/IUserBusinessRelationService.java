package com.kynsof.rrhh.doman.interfaces.services;

import com.kynsof.rrhh.doman.dto.UserBusinessRelationDto;
import java.util.UUID;

public interface IUserBusinessRelationService {
    public void create(UserBusinessRelationDto object);
    public void update(UserBusinessRelationDto object);
    public void delete(UUID id);
    public UserBusinessRelationDto findById(UUID id);
}