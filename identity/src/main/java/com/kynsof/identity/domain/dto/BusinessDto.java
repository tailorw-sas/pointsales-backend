package com.kynsof.identity.domain.dto;

import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class BusinessDto implements Serializable {
    private UUID id;
    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private String logo;
    private String ruc;
    private EBusinessStatus status;
    private boolean deleted;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    

    public BusinessDto(UUID id, String name, String latitude, String longitude, String description, String logo,  String ruc, EBusinessStatus status) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.status = status;
    }

    public BusinessDto(UUID id, String name, String latitude, String longitude, String description, String string) {
    }
}
