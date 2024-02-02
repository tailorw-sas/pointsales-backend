package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.BusinessDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IBusinessService {
    public void create(BusinessDto object);
    public void update(BusinessDto object);
    public void delete(UUID id);
    public BusinessDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter);
}