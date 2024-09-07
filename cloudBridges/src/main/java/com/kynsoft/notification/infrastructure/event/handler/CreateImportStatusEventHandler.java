package com.kynsoft.notification.infrastructure.event.handler;

import com.kynsoft.notification.domain.dtoEnum.ProcessStatus;
import com.kynsoft.notification.domain.event.CreateImportStatusEvent;
import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListProcessStatus;
import com.kynsoft.notification.infrastructure.repository.redis.ImportEmailListProcessStatusRedisRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateImportStatusEventHandler {

    private final ImportEmailListProcessStatusRedisRepository statusRedisRepository;

    public CreateImportStatusEventHandler(ImportEmailListProcessStatusRedisRepository statusRedisRepository) {
        this.statusRedisRepository = statusRedisRepository;
    }

    @EventListener
    public void onApplicationEvent(CreateImportStatusEvent event) {
        ImportEmailListProcessStatus processStatus = event.getProcessStatus();
        Optional<ImportEmailListProcessStatus> statusRedisEntity =statusRedisRepository.findByImportProcessId(processStatus.getImportProcessId());
        if (statusRedisEntity.isPresent()){
            if (!ProcessStatus.FINISHED.equals(statusRedisEntity.get().getStatus())) {
                statusRedisEntity.get().setStatus(processStatus.getStatus());
                statusRedisEntity.get().setHasError(processStatus.isHasError());
                statusRedisEntity.get().setExceptionMessage(processStatus.getExceptionMessage());
                statusRedisRepository.save(statusRedisEntity.get());
            }
        }else {
            statusRedisRepository.save(processStatus);
        }
    }
}
