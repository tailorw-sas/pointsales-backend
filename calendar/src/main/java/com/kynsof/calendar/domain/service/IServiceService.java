package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IServiceService {
    public void create(ServiceDto object);
    public void update(ServiceDto object);
    public void delete(UUID id);
    public ServiceDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
}