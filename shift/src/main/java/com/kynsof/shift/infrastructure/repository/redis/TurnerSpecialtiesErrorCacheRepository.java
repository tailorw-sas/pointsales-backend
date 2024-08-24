package com.kynsof.shift.infrastructure.repository.redis;

import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesExcelRowError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnerSpecialtiesErrorCacheRepository extends CrudRepository<TurnerSpecialtiesExcelRowError,String> {
    Page<TurnerSpecialtiesExcelRowError> findAllByImportProcessId(String importProcessId, Pageable pageable);

}
