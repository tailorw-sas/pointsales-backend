package com.kynsof.patients.infrastructure.entity.specifications.dinamic;

import lombok.Getter;
import lombok.Setter;

/**
 * @param key Getters
 */
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private SearchOperation operation; // Usa el enumerador Operation
    private Object value;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

}
