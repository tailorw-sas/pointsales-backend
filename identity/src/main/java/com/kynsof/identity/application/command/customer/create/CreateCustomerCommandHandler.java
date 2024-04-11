package com.kynsof.identity.application.command.customer.create;

import com.kynsof.identity.domain.dto.CustomerDto;
import com.kynsof.identity.domain.interfaces.service.ICustomerService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerCommandHandler implements ICommandHandler<CreateCustomerCommand> {

    private final ICustomerService customerService;

    @Autowired
    public CreateCustomerCommandHandler(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void handle(CreateCustomerCommand command) {
        CustomerDto customerDto = new CustomerDto(
                command.getId(),
                command.getFirstName(),
                command.getLastName(),
                command.getEmail()
        );

        this.customerService.create(customerDto);

    }
}
