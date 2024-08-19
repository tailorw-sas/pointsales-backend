package com.kynsoft.notification.infrastructure.entity;

import com.kynsoft.notification.domain.dto.AdvertisingContentDto;
import com.kynsoft.notification.domain.dto.ContentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingContent {
    @Id
    private UUID id;

    @Column()
    private String title;

    @Column()
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column()
    private String url;

    @Column()
    private String link;

    @ManyToOne()
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public AdvertisingContent(AdvertisingContentDto advertisingContentDto) {
        this.id = advertisingContentDto.getId();
        this.title = advertisingContentDto.getTitle();
        this.description = advertisingContentDto.getDescription();
        this.type = advertisingContentDto.getType();
        this.createdAt = advertisingContentDto.getCreatedAt() != null ? advertisingContentDto.getCreatedAt() : null;
        this.updatedAt = advertisingContentDto.getUpdatedAt() != null ? advertisingContentDto.getUpdatedAt() : null;
        this.url = advertisingContentDto.getUrl() != null ? advertisingContentDto.getUrl() : null;
        this.link = advertisingContentDto.getLink() != null ? advertisingContentDto.getLink() : null;
        this.tenant = advertisingContentDto.getTenant() != null ? new Tenant(advertisingContentDto.getTenant()) : null;
    }

    public AdvertisingContentDto toAggregate () {
        LocalDateTime createdDateTime = createdAt != null ? createdAt : null;
        LocalDateTime updateDateTime = createdAt != null ? createdAt : null;
        String urlS = url != null ? url : null;
        String linkS = link != null ? link : null;

        return new AdvertisingContentDto(
                id, 
                title, 
                description, 
                type, 
                createdDateTime, 
                updateDateTime, 
                urlS, 
                linkS,
                tenant != null ? tenant.toAggregate() : null
        );
    }

}
