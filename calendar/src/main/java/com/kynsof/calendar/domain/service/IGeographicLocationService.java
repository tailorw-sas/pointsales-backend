package com.kynsof.calendar.domain.service;


import com.kynsof.calendar.domain.dto.GeographicLocationDto;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IGeographicLocationService {
     GeographicLocationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID parentId, String name, String type);
}