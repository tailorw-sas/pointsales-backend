package com.kynsof.shift.infrastructure.excel.event;

import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesExcelRowError;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ImportErrorEvent extends ApplicationEvent {
    private final TurnerSpecialtiesExcelRowError error;
    public ImportErrorEvent(Object source, TurnerSpecialtiesExcelRowError error) {
        super(source);
        this.error = error;
    }
}
