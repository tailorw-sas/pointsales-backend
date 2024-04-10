package com.kynsof.rrhh.doman.interfaces.services;

import com.kynsof.rrhh.doman.dto.DeviceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IDeviceService {
    void create(DeviceDto object);
    void update(DeviceDto object);
    void delete(DeviceDto object);
    DeviceDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    PaginatedResponse findUsersByDeviceId(UUID deviceId, Pageable pageable);
}