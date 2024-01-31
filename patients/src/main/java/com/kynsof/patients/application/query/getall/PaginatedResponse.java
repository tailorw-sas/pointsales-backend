package com.kynsof.patients.application.query.getall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class PaginatedResponse {
    private List data;
    private Integer totalPages;
    private Integer totalElementsPage;
    private Long totalElements;
    private Integer size;
    private Integer page;
}
