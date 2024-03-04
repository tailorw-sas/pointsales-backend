package com.kynsof.store.domain.services;

import com.kynsof.store.domain.dto.OrderDetailDto;

import java.util.UUID;

public interface IOrderDetailService {
    UUID create(OrderDetailDto orderDetailDto);
    UUID update(OrderDetailDto orderDetailDto);
    void delete(UUID id);
    OrderDetailDto findById(UUID id);
    // Note: Las operaciones findAll y search pueden no aplicarse para OrderDetail de forma directa.
}

