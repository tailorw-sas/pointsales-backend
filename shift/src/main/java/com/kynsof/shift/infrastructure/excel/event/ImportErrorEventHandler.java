package com.kynsof.shift.infrastructure.excel.event;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesBulkCommand;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesExcelRowError;
import com.kynsof.shift.infrastructure.repository.redis.TurnerSpecialtiesErrorCacheRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportErrorEventHandler implements ApplicationListener<ImportErrorEvent> {
    private final TurnerSpecialtiesErrorCacheRepository errorCacheRepository;

    public ImportErrorEventHandler(TurnerSpecialtiesErrorCacheRepository errorCacheRepository) {
        this.errorCacheRepository = errorCacheRepository;
    }

    @Override
    public void onApplicationEvent(ImportErrorEvent event) {
        TurnerSpecialtiesExcelRowError error =event.getError();
        errorCacheRepository.save(error);
    }
}
