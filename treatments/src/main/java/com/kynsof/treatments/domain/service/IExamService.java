package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ExamDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IExamService {

    UUID create(ExamDto exam);

    void update(ExamDto exam);

    void delete(UUID id);

    ExamDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
