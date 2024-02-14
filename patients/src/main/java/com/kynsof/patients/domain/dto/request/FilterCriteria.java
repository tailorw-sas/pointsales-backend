package com.kynsof.patients.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterCriteria {
    private String key;
    private String operator;
    private String value;
}
