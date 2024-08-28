package com.kynsof.shift.infrastructure.entity.redis;

import com.kynsof.shift.domain.dto.ImportProcessStatusDto;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@AllArgsConstructor
@RedisHash(value = "processstatus",timeToLive = 3600)
public class ImportProcessStatus implements Serializable {
    @Id
    String id;
    private String status;
    @Indexed
    private String importProcessId;
    private boolean hasError;
    private String exceptionMessage;

    public ImportProcessStatusDto toAggregate(){
        return new ImportProcessStatusDto(this.id,
                this.status,
                this.importProcessId,
                this.hasError,
                this.exceptionMessage);
    }
}
