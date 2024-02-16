package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.application.PaginatedResponse;
import com.kynsof.calendar.domain.dto.QualificationDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IQualificationService {
    public void create(QualificationDto qualification);
    public void update(QualificationDto qualification);
    public void delete(UUID id);
    public QualificationDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idQualification, String filter);
}