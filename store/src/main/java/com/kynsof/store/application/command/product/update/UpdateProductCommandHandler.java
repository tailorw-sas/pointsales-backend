package com.kynsof.store.application.command.product.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.dto.SubcategoryEntityDto;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import com.kynsof.store.domain.services.IProductService;
import com.kynsof.store.domain.services.ISubcategoryService;
import com.kynsof.store.domain.services.ISupplierService;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCommandHandler implements ICommandHandler<UpdateProductCommand> {

    private final IProductService productService;
    private final ISupplierService supplierService;
    private final ISubcategoryService subcategoryService;

    public UpdateProductCommandHandler(IProductService productService, ISupplierService supplierService, ISubcategoryService subcategoryService) {
        this.productService = productService;
        this.supplierService = supplierService;
        this.subcategoryService = subcategoryService;
    }

    @Override
    public void handle(UpdateProductCommand command) {
        SupplierEntityDto supplierEntityDto = supplierService.findById(command.getSupplierId());
        SubcategoryEntityDto subcategoryEntityDto = subcategoryService.findById(command.getSubcategoryId());
        ProductEntityDto productEntityDto = new ProductEntityDto(command.getProductId(), command.getName(), command.getDescription(), command.getPrice(), command.getQuantityInStock(),
                command.getStatus(),command.getSubcategoryId(),command.getSupplierId(), supplierEntityDto,subcategoryEntityDto);
         productService.update(productEntityDto);
    }
}
