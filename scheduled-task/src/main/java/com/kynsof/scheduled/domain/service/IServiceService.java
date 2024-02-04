package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IServiceService {
    public void create(ServiceDto object);
    public void update(ServiceDto object);
    public void delete(UUID id);
    public ServiceDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
}