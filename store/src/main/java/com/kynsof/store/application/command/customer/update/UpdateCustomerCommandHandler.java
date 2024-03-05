package com.kynsof.store.application.command.customer.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CustomerDto;
import com.kynsof.store.domain.services.ICustomerService;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerCommandHandler implements ICommandHandler<UpdateCustomerCommand> {

    private final ICustomerService categoryService;

    public UpdateCustomerCommandHandler(ICustomerService categoryService) {

        this.categoryService = categoryService;
    }

    @Override
    public void handle(UpdateCustomerCommand command) {
        categoryService.update(new CustomerDto(command.getId(), command.getFirstName(), command.getLastName(),
                command.getEmail(),command.getPhone()));
    }
}

