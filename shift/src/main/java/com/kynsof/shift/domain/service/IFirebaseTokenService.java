package com.kynsof.shift.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.shift.domain.dto.FirebaseTokenDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IFirebaseTokenService {
    void create(FirebaseTokenDto object);
    void update(FirebaseTokenDto object);
    void delete(FirebaseTokenDto delete);
    FirebaseTokenDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}