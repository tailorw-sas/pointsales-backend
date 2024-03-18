package com.kynsof.share.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    protected UUID id;
    @Column(nullable = true)
    protected LocalDateTime createdAt;
    @Column(nullable = true)
    protected LocalDateTime updatedAt;
    @Column(nullable = true)
    protected LocalDateTime deletedAt;

    public BaseEntity() {
    }

}
