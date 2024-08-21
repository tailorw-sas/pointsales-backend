package com.kynsof.shift.domain.service;

import com.kynsof.shift.domain.dto.QualificationDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IQualificationService {
    public void create(QualificationDto qualification);
    public void update(QualificationDto qualification);
    public void delete(UUID id);
    public QualificationDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idQualification, String filter);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    Long countByDescription(String description);
}