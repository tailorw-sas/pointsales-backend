package com.kynsof.shift.application.command.tunerSpecialties.importExcel;

import lombok.Getter;

@Getter
public class ImportTurnerSpecialtiesRequest {
    private final byte[] file;
    private final String importProcessId;

    public ImportTurnerSpecialtiesRequest(byte[] file, String importProcessId) {
        this.file = file;
        this.importProcessId = importProcessId;
    }
}
