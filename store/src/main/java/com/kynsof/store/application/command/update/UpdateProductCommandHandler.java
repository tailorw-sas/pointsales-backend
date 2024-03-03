package com.kynsof.store.application.command.update;



import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCommandHandler implements ICommandHandler<UpdateProductCommand> {

//    private final IProductService productService;

    public UpdateProductCommandHandler() {
    }

    @Override
    public void handle(UpdateProductCommand command) {

    }
}
