package com.kynsof.identity.domain.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModuleDto {
    protected UUID id;
    private String name;
    private UUID image;
    private String description;
    private Set<PermissionDto> permissions = new HashSet<>();

    public ModuleDto(UUID id, String name, UUID image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

}
