package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ISessionService {

    void create(SessionDto object);

    void update(SessionDto object);

    void delete(UUID id);

    SessionDto findById(UUID id);

    SessionDto getById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    List<SessionDto> findAll();
}







