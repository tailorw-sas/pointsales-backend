package com.kynsoft.notification.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AFileDto {
    private UUID id;
    private String name;
    private String url;
    private String objetId;

    public AFileDto(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
