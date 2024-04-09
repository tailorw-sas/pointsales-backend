package com.kynsof.rrhh.doman.interfaces.services;

import com.kynsof.rrhh.doman.dto.DeviceDto;
import java.util.UUID;

public interface IDeviceService {
    public void create(DeviceDto object);
    public void update(DeviceDto object);
    public void delete(UUID id);
    public DeviceDto findById(UUID id);
}