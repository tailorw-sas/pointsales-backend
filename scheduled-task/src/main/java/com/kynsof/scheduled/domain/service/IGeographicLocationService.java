package com.kynsof.scheduled.domain.service;


import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.GeographicLocationDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IGeographicLocationService {
     GeographicLocationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID parentId, String name, String type);
}