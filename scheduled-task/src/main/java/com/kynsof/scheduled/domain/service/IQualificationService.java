package com.kynsof.scheduled.domain.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.domain.dto.QualificationDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IQualificationService {
    public void create(QualificationDto qualification);
    public void delete(UUID id);
    public QualificationDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idQualification);
}