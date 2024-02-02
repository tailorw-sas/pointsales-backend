package com.kynsof.scheduled.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDto {
    private UUID id;
    private String name;
    private String description;
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public BusinessDto(UUID id, String name, String description, byte[] logo, String ruc, EBusinessStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.status = status;
    }

}
