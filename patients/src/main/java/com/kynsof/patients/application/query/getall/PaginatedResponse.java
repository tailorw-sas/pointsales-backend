package com.kynsof.patients.application.query.getall;

import com.kynsof.patients.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginatedResponse implements IResponse {
    private List data;
    private Integer totalPages;
    private Integer totalElementsPage;
    private Long totalElements;
    private Integer size;
    private Integer page;
}
