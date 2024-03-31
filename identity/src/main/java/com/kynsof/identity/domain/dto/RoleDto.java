package com.kynsof.identity.domain.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
    private String description;
    private RoleStatusEnm status;

    private boolean deleted;

    /**
     * Usar este constructor en le create y update
     * @param id
     * @param name
     * @param description
     * @param status 
     */
    public RoleDto(UUID id, String name, String description, RoleStatusEnm status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

}
