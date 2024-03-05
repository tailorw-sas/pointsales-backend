package com.kynsof.store.application.command.customer.update;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.customer.create.CustomerRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCustomerCommand implements ICommand {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public UpdateCustomerCommand(UUID id,String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public static UpdateCustomerCommand fromRequest(UUID customerId, CustomerRequest request) {
        return new UpdateCustomerCommand(
                customerId,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone()
        );
    }


    @Override
    public ICommandMessage getMessage() {
        // Implementación específica
        return new UpdateCustomerMessage();
    }
}
