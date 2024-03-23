package com.kynsof.identity.domain.dto;

import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionDto {
    private UUID id;
    private String code;
    private String description;
    private String module;
    private PermissionStatusEnm status;

    /**
     * Usar este constructor en el create.
     * @param id
     * @param code
     * @param description 
     * @param module 
     */
    public PermissionDto(UUID id, String code, String description, String module) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.module = module;
    }

    /**
     * Usar este constructor en el update.
     * @param id
     * @param code
     * @param description
     * @param status 
     */
    public PermissionDto(UUID id, String code, String description, PermissionStatusEnm status) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.status = status;
    }

}
