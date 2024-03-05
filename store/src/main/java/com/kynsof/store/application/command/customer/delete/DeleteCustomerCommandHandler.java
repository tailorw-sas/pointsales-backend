package com.kynsof.store.application.command.customer.delete;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.services.ICustomerService;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerCommandHandler implements ICommandHandler<DeleteCustomerCommand> {

    private final ICustomerService customerService;

    public DeleteCustomerCommandHandler(ICustomerService categoryService) {
        this.customerService = categoryService;
    }

    @Override
    public void handle(DeleteCustomerCommand command) {
        customerService.delete(command.getCategoryId());
    }
}
