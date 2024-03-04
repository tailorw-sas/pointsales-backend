package com.kynsof.store.domain.services;



import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface IOrderService {
    UUID create(OrderEntityDto orderDto);
    UUID update(OrderEntityDto orderDto);
    void delete(UUID id);
    OrderEntityDto findById(UUID id);
    PaginatedResponse findAll(Pageable pageable);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
