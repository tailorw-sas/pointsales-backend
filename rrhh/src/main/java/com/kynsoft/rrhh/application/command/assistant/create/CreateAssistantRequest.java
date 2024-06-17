package com.kynsoft.rrhh.application.command.assistant.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAssistantRequest {

    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String phoneNumber;
    private String image;
    private String department;
}