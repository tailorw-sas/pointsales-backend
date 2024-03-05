package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.UserDto;
import java.util.UUID;

public interface IUserService {
    public void create(UserDto user);
    public void update(UserDto user);
    public void delete(UUID id);
    public UserDto findById(UUID id);    
}