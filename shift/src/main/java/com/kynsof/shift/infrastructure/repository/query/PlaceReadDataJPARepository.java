package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceReadDataJPARepository extends JpaRepository<Place, UUID>, JpaSpecificationExecutor<Place> {
    Page<Place> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Place b WHERE b.code = :code AND b.id <> :id")
    Long countByCodeAndNotId(@Param("code") String code, @Param("id") UUID id);

    @Query("SELECT COUNT(b) FROM Place b WHERE b.name = :name AND b.id <> :id")
    Long countByNameAndNotId(@Param("name") String name, @Param("id") UUID id);

}
