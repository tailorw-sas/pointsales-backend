package com.kynsof.identity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class RolDto {
    private UUID id;
    private String name;
    private String description;
}
