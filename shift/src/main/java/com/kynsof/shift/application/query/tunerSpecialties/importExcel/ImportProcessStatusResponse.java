package com.kynsof.shift.application.query.tunerSpecialties.importExcel;

import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;

@Getter
public class ImportProcessStatusResponse implements IResponse {
    private final String status;

    public ImportProcessStatusResponse(String status) {
        this.status = status;
    }
}
