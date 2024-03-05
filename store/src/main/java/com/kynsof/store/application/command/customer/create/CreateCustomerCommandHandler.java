package com.kynsof.store.application.command.customer.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.CustomerDto;
import com.kynsof.store.domain.services.ICustomerService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCustomerCommandHandler implements ICommandHandler<CreateCustomerCommand> {

    private final ICustomerService categoryService;
    public CreateCustomerCommandHandler(ICustomerService categoryService) {

        this.categoryService = categoryService;
    }

    @Override
    public void handle(CreateCustomerCommand command) {
        UUID id = categoryService.create(new CustomerDto(UUID.randomUUID(), command.getFirstName(),command.getLastName(),command.getEmail(),
                command.getPhone()));
        command.setId(id);
    }
}
