package com.kynsof.shift.infrastructure.repository.redis;

import com.kynsof.shift.infrastructure.entity.redis.ImportProcessStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImportProcessStatusRepository extends CrudRepository<ImportProcessStatus,String>{
    Optional<ImportProcessStatus> findByImportProcessId(String importProcessId);
}
