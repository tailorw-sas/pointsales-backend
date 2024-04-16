package com.kynsoft.notification.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingContentDto {
    private UUID id;
    private String name;
    private String description;
    private ContentType type;
}
