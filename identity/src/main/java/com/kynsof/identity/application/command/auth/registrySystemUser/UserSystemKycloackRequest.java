package com.kynsof.identity.application.command.auth.registrySystemUser;

import com.kynsof.share.core.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSystemKycloackRequest {
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private UserType userType;
    //List<String> roles;
}
