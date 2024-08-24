package com.kynsof.shift.infrastructure.repository.redis;

import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TurnerSpecialtiesCacheRepository extends CrudRepository<TurnerSpecialtiesCache,String> {

    Page<TurnerSpecialtiesCache> findTurnerSpecialtiesCacheByImportProcessId(String importProcessId, Pageable pageable);

    Page<TurnerSpecialtiesCache> findAllByImportProcessId(String importProcessId, Pageable pageable);
}
