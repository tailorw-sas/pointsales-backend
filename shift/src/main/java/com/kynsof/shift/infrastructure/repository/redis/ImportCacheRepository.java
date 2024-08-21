package com.kynsof.shift.infrastructure.repository.redis;

import com.kynsof.shift.domain.excel.ImportCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ImportCacheRepository extends CrudRepository<ImportCache,String> {

    Page<ImportCache> findAllByImportProcessId(String importProcessId, Pageable pageable);
}
