package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.PaymentDevDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IPaymentDevService {

    void create(PaymentDevDto paymentDevDto);

    void update(PaymentDevDto paymentDevDto);

    void delete(PaymentDevDto paymentDevDto);

    PaymentDevDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

    Long countByReferenceAndNotId(String name, UUID id);
}
