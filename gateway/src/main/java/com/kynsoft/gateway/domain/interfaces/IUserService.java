package com.kynsoft.gateway.domain.interfaces;

import com.kynsoft.gateway.application.dto.RegisterDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.UUID;


public interface IUserService {

    void changeStatus(UUID id, String status);

    List<UserRepresentation> searchUserByUsername(String username);
    void updateUser(String id, RegisterDTO registerDTO);

    Boolean changeUserPassword(String userId, String oldPassword, String newPassword);
}

