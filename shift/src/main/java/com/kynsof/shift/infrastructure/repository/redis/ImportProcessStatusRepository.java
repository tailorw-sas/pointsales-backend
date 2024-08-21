package com.kynsof.shift.infrastructure.repository.redis;

import com.kynsof.shift.domain.excel.ImportProcessStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImportProcessStatusRepository extends CrudRepository<ImportProcessStatusEntity,String>{
    Optional<ImportProcessStatusEntity> findByImportProcessId(String importProcessId);
}
