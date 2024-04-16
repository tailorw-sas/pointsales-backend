package com.kynsoft.notification.infrastructure.entity;

import com.kynsoft.notification.domain.dto.AdvertisingContentDto;
import com.kynsoft.notification.domain.dto.ContentType;
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
public class AdvertisingContent {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    public AdvertisingContent(AdvertisingContentDto advertisingContentDto) {
        this.id = advertisingContentDto.getId();
        this.name = advertisingContentDto.getName();
        this.description = advertisingContentDto.getDescription();
        this.type = advertisingContentDto.getType();
    }

    public AdvertisingContentDto toAggregate () {
        return new AdvertisingContentDto(id, name, description, type);
    }

}
