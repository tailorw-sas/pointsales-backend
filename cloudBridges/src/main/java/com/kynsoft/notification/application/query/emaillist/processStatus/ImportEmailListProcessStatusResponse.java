package com.kynsoft.notification.application.query.emaillist.processStatus;

import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImportEmailListProcessStatusResponse implements IResponse {
    private final String status;
}
