package com.kynsof.store.application.command.product.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.IProductService;
import com.kynsof.store.domain.services.ISubcategoryService;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateProductCommandHandler implements ICommandHandler<CreateProductCommand> {

   private final IProductService productService;
   private final ISupplierService supplierService;
   private final ISubcategoryService subcategoryService;
    public CreateProductCommandHandler(IProductService productService, ISupplierService supplierService, ISubcategoryService subcategoryService) {
        this.productService = productService;
        this.supplierService = supplierService;
        this.subcategoryService = subcategoryService;
    }

    @Override
    public void handle(CreateProductCommand command) {
        SupplierEntityDto supplierEntityDto = supplierService.findById(command.getSupplierId());
        SubcategoryEntityDto subcategoryEntityDto = subcategoryService.findById(command.getSubcategoryId());
        ProductEntityDto productEntityDto = new ProductEntityDto(command.getId(), command.getName(), command.getDescription(), command.getPrice(),
                command.getCost(),command.getQuantityInStock(),
                command.getStatus(),command.getSubcategoryId(),command.getSupplierId(), supplierEntityDto,subcategoryEntityDto);
       UUID id = productService.create(productEntityDto);
       command.setId(id);
    }
}
