package com.kynsoft.notification.application.query.emaillist.search;

import com.kynsof.share.core.domain.bus.query.IQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.infrastructure.specifications.SearchCriteria;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class GetSearchEmailListQuery implements IQuery {
    private final SearchRequest searchRequest;

    public GetSearchEmailListQuery(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }
}
