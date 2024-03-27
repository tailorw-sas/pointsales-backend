package com.kynsof.identity.application.command.business.update;

import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import com.kynsof.share.core.application.FileRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessRequest {
    private UUID id;
    private String name;    
    private String latitude;
    private String longitude;
    private String description;
    private FileRequest logo;
    private String ruc;
    private EBusinessStatus status;
}