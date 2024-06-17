package com.kynsoft.rrhh.application.command.assistant.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateAssistantRequest {
    private String serialId;
    private String ip;
    private UUID businessId;
}
