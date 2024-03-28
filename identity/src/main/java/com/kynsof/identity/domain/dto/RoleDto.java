package com.kynsof.identity.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
    private String description;
    private com.kynsof.identity.domain.dto.RoleStatusEnm status;

    /**
     * Usar este constructor en le create y update
     * @param id
     * @param name
     * @param description
     * @param status 
     */
    public RoleDto(UUID id, String name, String description, com.kynsof.identity.domain.dto.RoleStatusEnm status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

}
