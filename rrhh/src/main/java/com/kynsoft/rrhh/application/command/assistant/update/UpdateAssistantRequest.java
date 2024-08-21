package com.kynsoft.rrhh.application.command.assistant.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAssistantRequest {
    private String identification;
    private String code;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String phoneNumber;
    private String image;
    private String department;
}
