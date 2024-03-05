package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.domain.dto.UserDto;
import com.kynsof.patients.domain.service.IUserService;
import com.kynsof.patients.infrastructure.entity.User;
import com.kynsof.patients.infrastructure.repositories.command.UserWriteDataJPARepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserWriteDataJPARepository userCommandRepository;

    @Override
    public void create(UserDto user) {
        this.userCommandRepository.save(new User(user));
    }

    @Override
    public void update(UserDto user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
