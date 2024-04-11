package com.kynsof.rrhh.application.command.user.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String identification;
    private String name;
    private String lastName;
    private String email;
    private byte[] image;
}
