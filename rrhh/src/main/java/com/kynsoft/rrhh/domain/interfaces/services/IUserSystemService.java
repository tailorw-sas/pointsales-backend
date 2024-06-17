package com.kynsoft.rrhh.domain.interfaces.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.rrhh.domain.dto.UserSystemDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUserSystemService {
    UUID create(UserSystemDto userSystemDto);

    void update(UserSystemDto patients);

    void delete(UUID id);

    UserSystemDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
