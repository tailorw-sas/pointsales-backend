package com.kynsof.shift.domain.dto;

import com.kynsof.shift.infrastructure.entity.redis.ImportProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImportProcessStatusDto {
    private String id;
    private String status;
    private String importProcessId;
    private boolean hasError;
    private String exceptionMessage;

    public ImportProcessStatusDto(String status, String importProcessId) {
        this.status = status;
        this.importProcessId = importProcessId;
    }

    public ImportProcessStatus toAggregate() {
        return new ImportProcessStatus(this.id,
                this.status,
                this.importProcessId,
                this.hasError,
                this.exceptionMessage);

    }
}
