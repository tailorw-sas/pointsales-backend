package com.kynsoft.rrhh.infrastructure.identity;

import com.kynsoft.rrhh.domain.dto.DeviceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "device")
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String serialId;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = true)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public Device(DeviceDto device) {
        this.id = device.getId();
        this.serialId = device.getSerialId();
        this.ip = device.getIp();
        this.business = new Business(device.getBusiness());
        this.deleted = device.isDeleted();
    }

    public DeviceDto toAggregate () {
        return new DeviceDto(id, serialId, ip, business.toAggregate());
    }

}
