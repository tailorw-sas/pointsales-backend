package com.kynsof.share.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

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
    @Column(nullable = true)
    protected boolean deleted;

    public BaseEntity() {
        this.deleted = false;
    }

}
