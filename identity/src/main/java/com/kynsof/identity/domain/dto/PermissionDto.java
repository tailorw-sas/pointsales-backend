package com.kynsof.identity.domain.dto;

import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PermissionDto {

    private UUID id;
    private String code;
    private String description;
    private ModuleDto module;
    private PermissionStatusEnm status;
    private String action;
    private boolean deleted = false;

    /**
     * Usar este constructor en el create.
     *
     * @param id
     * @param code
     * @param description
     * @param module
     */
    public PermissionDto(UUID id, String code, String description, ModuleDto module, String action) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.module = module;
        this.action = action;
    }

    public PermissionDto(UUID id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    /**
     * Usar este constructor en el update.
     *
     * @param id
     * @param code
     * @param description
     * @param module
     * @param status
     */
    public PermissionDto(UUID id, String code, String description, ModuleDto module, PermissionStatusEnm status, String action) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.module = module;
        this.status = status;
        this.action = action;
    }

}
