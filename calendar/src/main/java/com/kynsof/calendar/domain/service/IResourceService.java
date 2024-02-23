package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IResourceService {
    public void create(ResourceDto object);
    public void update(ResourceDto object);
    public void delete(UUID id);
    public ResourceDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}