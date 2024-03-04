package com.kynsof.store.infrastructure.repositories.command;

import com.kynsof.store.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductWriteDataJPARepository extends JpaRepository<Product, UUID> {

}
