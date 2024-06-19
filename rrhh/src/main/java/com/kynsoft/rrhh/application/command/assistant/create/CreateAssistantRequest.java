package com.kynsoft.rrhh.application.command.assistant.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAssistantRequest {
    private UUID id;
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String status;
    private String phoneNumber;
    private String image;
    private String department;
}