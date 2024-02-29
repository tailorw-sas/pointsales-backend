package com.kynsof.share.core.domain.request;

import com.kynsof.share.core.infrastructure.specifications.LogicalOperation;
import com.kynsof.share.core.infrastructure.specifications.SearchOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterCriteria {
    private String key;
    private SearchOperation operator;
    private Object value;
    private LogicalOperation logicalOperation = LogicalOperation.AND;
}
