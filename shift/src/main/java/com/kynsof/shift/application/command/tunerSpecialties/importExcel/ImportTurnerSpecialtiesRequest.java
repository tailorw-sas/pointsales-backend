package com.kynsof.shift.application.command.tunerSpecialties.importExcel;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ImportTurnerSpecialtiesRequest {
    private final byte[] file;
    private final String importProcessId;
    private final String bussinessId;

    public ImportTurnerSpecialtiesRequest(byte[] file, String importProcessId, String bussinessId) {
        this.file = file;
        this.importProcessId = importProcessId;
        this.bussinessId = bussinessId;
    }
}
