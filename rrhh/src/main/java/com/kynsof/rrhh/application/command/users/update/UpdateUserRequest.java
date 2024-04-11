package com.kynsof.rrhh.application.command.users.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    private String identification;
    private String name;
    private String lastName;
    private String email;
    private byte[] image;
}
