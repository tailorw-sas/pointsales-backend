package com.kynsof.patients.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProvinceDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UUID id;
    private final String name;
}
