package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IGeographicLocationService {
     GeographicLocationDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID parentId,  String name, String type);
}