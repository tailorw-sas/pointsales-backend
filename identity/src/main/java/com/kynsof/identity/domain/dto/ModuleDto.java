package com.kynsof.identity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModuleDto {
    protected UUID id;
    private String name;
    private String image;
    private String description;
    private Set<PermissionDto> permissions = new HashSet<>();

    public ModuleDto(UUID id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

}
