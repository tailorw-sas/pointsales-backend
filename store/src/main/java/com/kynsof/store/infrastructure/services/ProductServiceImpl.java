package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.store.application.query.product.ProductResponse;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.services.IProductService;
import com.kynsof.store.infrastructure.entity.Product;
import com.kynsof.store.infrastructure.entity.Subcategory;
import com.kynsof.store.infrastructure.entity.Supplier;
import com.kynsof.store.infrastructure.repositories.command.ProductWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.ProductReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductWriteDataJPARepository repositoryCommand;
    @Autowired
    private ProductReadDataJPARepository repositoryQuery;
    @Override
    public UUID create(ProductEntityDto productDto) {
        Product save = this.repositoryCommand.save(new Product(productDto));
        return save.getId();
    }

    @Override
    public UUID update(ProductEntityDto productDto) {
        if (productDto == null || productDto.getId() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        return repositoryQuery.findById(productDto.getId())
                .map(product -> {
                    product.setName(productDto.getName() != null ? productDto.getName() : product.getName());
                    product.setDescription(productDto.getDescription() != null ? productDto.getDescription() : product.getDescription());
                    product.setPrice(productDto.getPrice() != null ? productDto.getPrice() : product.getPrice());
                    product.setCost(productDto.getCost() != null ? productDto.getPrice() : product.getPrice());
                    product.setQuantityInStock(productDto.getQuantityInStock() != null ? productDto.getQuantityInStock() : product.getQuantityInStock());
                    product.setStatus(productDto.getStatus() != null ? productDto.getStatus() : product.getStatus());

                    if (productDto.getSubcategoryId() != null) {

                        product.setSubcategory(new Subcategory(productDto.getSubcategoryEntityDto()));
                    }
                    if (productDto.getSupplierId() != null) {
                        product.setSupplier(new Supplier(productDto.getSupplierEntityDto()));
                    }

                    product.setUpdatedAt(LocalDateTime.now());
                    return repositoryCommand.save(product);
                })
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productDto.getId() + " not found"))
                .getId();
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public ProductEntityDto findById(UUID id) {
        Optional<Product> product = this.repositoryQuery.findById(id);
        if (product.isPresent()) {
            return product.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Subcategory not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<Product> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Product> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Product> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Product> data) {
        List<ProductResponse> patients = new ArrayList<>();
        for (Product p : data.getContent()) {
            patients.add(new ProductResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}