package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Vaccine;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface VaccineReadDataJPARepository extends JpaRepository<Vaccine, UUID>, JpaSpecificationExecutor<Vaccine> {
    Page<Vaccine> findAll(Specification specification, Pageable pageable);
    @Query("SELECT v FROM Vaccine v WHERE v.minAge <= :age AND v.maxAge >= :age")
    Page<Vaccine> findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(@Param("age") double age, Pageable pageable);

    
    @Query("SELECT COUNT(b) FROM Vaccine b WHERE b.name = :name AND b.id <> :id")
    Long countByNameAndNotId(@Param("name") String name, @Param("id") UUID id);

}
