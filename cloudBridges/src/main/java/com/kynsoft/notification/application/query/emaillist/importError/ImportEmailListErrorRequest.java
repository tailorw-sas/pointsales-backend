package com.kynsoft.notification.application.query.emaillist.importError;

import com.kynsof.share.core.domain.request.FilterCriteria;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class ImportEmailListErrorRequest {
    private final String query;
    private final Pageable pageable;


    public ImportEmailListErrorRequest(String query, Pageable pageable) {
        this.query = query;
        this.pageable = pageable;
    }
}
