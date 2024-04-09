package com.kynsof.rrhh.module.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class BusinessDto {
    private UUID id;
    private String name;
}
