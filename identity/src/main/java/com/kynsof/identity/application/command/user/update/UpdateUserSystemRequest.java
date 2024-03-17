package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserSystemRequest {
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private UserStatus status;
}