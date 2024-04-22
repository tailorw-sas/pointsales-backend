package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDto {
    private UUID id;
    private String name;
    private String description;
}
