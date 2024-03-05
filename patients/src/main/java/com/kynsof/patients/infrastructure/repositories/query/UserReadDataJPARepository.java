package com.kynsof.patients.infrastructure.repositories.query;

import com.kynsof.patients.infrastructure.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserReadDataJPARepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Page<User> findAll(Specification specification, Pageable pageable);
}
