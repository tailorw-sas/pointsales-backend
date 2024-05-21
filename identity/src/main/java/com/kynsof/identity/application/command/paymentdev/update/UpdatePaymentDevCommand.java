package com.kynsof.identity.application.command.paymentdev.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePaymentDevCommand implements ICommand {

    private UUID id;
    private Double payment;
    private String reference;

    public UpdatePaymentDevCommand(UUID id, Double payment, String reference) {
        this.id = id;
        this.payment = payment;
        this.reference = reference;
    }

    public static UpdatePaymentDevCommand fromRequest(UpdatePaymentDevRequest request, UUID id) {
        return new UpdatePaymentDevCommand(
                id, 
                request.getPayment(), 
                request.getReference());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdatePaymentDevMessage(id);
    }
}
