package com.kynsoft.notification.infrastructure.entity;

import com.kynsof.share.core.domain.BaseEntity;
import com.kynsoft.notification.domain.dto.AFileDto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AFile extends BaseEntity {

    private String name;
    private String url;

    public AFile(AFileDto file) {
        this.id = file.getId();
        this.name = file.getName();
        this.url = file.getUrl();
    }

    public AFileDto toAggregate () {
        return new AFileDto(id, name,  url);
    }
}