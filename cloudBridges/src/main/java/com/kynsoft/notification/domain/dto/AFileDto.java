package com.kynsoft.notification.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AFileDto {
    private UUID id;
    private String name;
    private String microServiceName;
    private String url;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public AFileDto(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public AFileDto(UUID id, String name, String microServiceName, String url) {
        this.id = id;
        this.name = name;
        this.microServiceName = microServiceName;
        this.url = url;
    }

}
