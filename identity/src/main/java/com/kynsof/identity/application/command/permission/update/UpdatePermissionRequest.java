package com.kynsof.identity.application.command.permission.update;

import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePermissionRequest {

    private UUID id;
    private String code;
    private String description;
    private UUID idModule;
    private PermissionStatusEnm status;
}
