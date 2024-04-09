package com.kynsof.rrhh.infrastructure.identity;

import com.kynsof.rrhh.doman.dto.DeviceDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
