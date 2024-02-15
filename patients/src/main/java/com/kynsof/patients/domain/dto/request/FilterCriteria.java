package com.kynsof.patients.domain.dto.request;

import com.kynsof.patients.infrastructure.entity.specifications.dinamic.SearchOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterCriteria {
    private String key;
    private SearchOperation operator;
    private String value;
}
