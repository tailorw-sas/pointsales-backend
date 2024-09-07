package com.kynsoft.notification.infrastructure.repository.redis;

import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListProcessStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImportEmailListProcessStatusRedisRepository
        extends CrudRepository<ImportEmailListProcessStatus,String> {

    Optional<ImportEmailListProcessStatus> findByImportProcessId(String importProcessId);
}
