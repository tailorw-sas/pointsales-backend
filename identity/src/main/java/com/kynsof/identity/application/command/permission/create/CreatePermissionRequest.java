package com.kynsof.identity.application.command.permission.create;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionRequest {

    private String code;
    private String description;
    private UUID idModule;
}
