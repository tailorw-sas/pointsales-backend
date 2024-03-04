package com.kynsof.store.application.command.deleted;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.IProductService;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements ICommandHandler<DeleteProductCommand> {


    private IProductService productService;

//    public DeleteProductCommandHandler(IProductService productService) {
//        this.productService = productService;
//    }

    @Override
    public void handle(DeleteProductCommand command) {

    }
}

