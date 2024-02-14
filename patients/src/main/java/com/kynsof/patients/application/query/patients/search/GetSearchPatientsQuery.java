package com.kynsof.patients.application.query.patients.search;

import com.kynsof.patients.domain.bus.query.IQuery;
import com.kynsof.patients.domain.dto.request.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetSearchPatientsQuery implements IQuery {

    private Pageable pageable;
    private List<FilterCriteria> filter;
    private String query;
}
