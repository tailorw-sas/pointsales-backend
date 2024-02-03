package com.kynsof.scheduled.application.command.business.update;

import com.kynsof.scheduled.domain.dto.EBusinessStatus;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessRequest {
    private UUID id;
    private String name;
    private String description;
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;
}