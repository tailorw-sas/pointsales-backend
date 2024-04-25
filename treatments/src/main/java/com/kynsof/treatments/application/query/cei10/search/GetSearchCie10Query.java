package com.kynsof.treatments.application.query.cei10.search;

import com.kynsof.share.core.domain.bus.query.IQuery;
import com.kynsof.share.core.domain.request.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetSearchCie10Query implements IQuery {

    private Pageable pageable;
    private List<FilterCriteria> filter;
    private String query;
}
