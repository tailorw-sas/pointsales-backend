package com.kynsof.shift.domain.dto;

import com.kynsof.shift.domain.dto.enumType.EResourceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ResourceDto implements Serializable {

    private UUID id;
    private String name;
    private String image;
    private EResourceStatus status;
    private String code;
    private String externalCode;

    public ResourceDto(UUID id, String name, String image, EResourceStatus status, String code) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.code = code;
    }

    public ResourceDto(UUID id, String name, String image, EResourceStatus status, String code, String externalCode) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.code = code;
        this.externalCode = externalCode;
    }
}
