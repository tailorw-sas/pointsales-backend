package com.kynsoft.notification.domain.event.handler;

import com.kynsoft.notification.domain.event.CreateImportErrorEvent;
import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListErrorRow;
import com.kynsoft.notification.infrastructure.repository.redis.ImportEmailListErrorRedisRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreateImportErrorEventHandler {
    private final ImportEmailListErrorRedisRepository importErrorRedisRepository;

    public CreateImportErrorEventHandler(ImportEmailListErrorRedisRepository importErrorRedisRepository) {
        this.importErrorRedisRepository = importErrorRedisRepository;
    }


    @EventListener
    public void onApplicationEvent(CreateImportErrorEvent event) {
        ImportEmailListErrorRow error = event.getImportEmailListErrorRow();
        importErrorRedisRepository.save(error);
    }
}
