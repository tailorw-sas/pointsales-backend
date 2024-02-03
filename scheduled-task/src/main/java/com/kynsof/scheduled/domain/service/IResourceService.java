package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IResourceService {
    public void create(ResourceDto object);
    public void update(ResourceDto object);
    public void delete(UUID id);
    public ResourceDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
}