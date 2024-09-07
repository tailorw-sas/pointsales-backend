package com.kynsoft.notification.infrastructure.repository.redis;

import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListErrorRow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ImportEmailListErrorRedisRepository extends CrudRepository<ImportEmailListErrorRow,String> {

    Page<ImportEmailListErrorRow> findAllByImportProcessId(String importProcessId, Pageable pageable);

    boolean existsByImportProcessId(String importProcessId);
}
