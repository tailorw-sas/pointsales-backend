package com.kynsof.store.infrastructure.repositories.command;

import com.kynsof.store.infrastructure.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryWriteDataJPARepository extends JpaRepository<Category, UUID> {

}
