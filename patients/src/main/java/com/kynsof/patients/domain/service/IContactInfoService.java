package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IContactInfoService {
    UUID create(ContactInfoDto patients);

    UUID update(ContactInfoDto patients);

    void delete(UUID id);

    ContactInfoDto findById(UUID id);

    PaginatedResponse findAll(Pageable pageable);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}