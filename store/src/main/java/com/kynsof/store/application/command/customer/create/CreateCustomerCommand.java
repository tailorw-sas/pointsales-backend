package com.kynsof.store.application.command.customer.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCustomerCommand implements ICommand {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public CreateCustomerCommand(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    public static CreateCustomerCommand fromRequest(CustomerRequest request) {
        return new CreateCustomerCommand(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone()
        );
    }

    @Override
    public ICommandMessage getMessage() {

        return new CreateCustomerMessage(id);
    }
}
