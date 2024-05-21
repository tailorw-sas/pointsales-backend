package com.kynsof.identity.application.command.paymentdev.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePaymentDevCommand implements ICommand {
    private UUID id;
    private UUID userId;
    private Double payment;
    private String reference;

    public CreatePaymentDevCommand(UUID userId, Double payment, String reference) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.payment = payment;
        this.reference = reference;
    }

    public static CreatePaymentDevCommand fromRequest(CreatePaymentDevRequest request) {
        return new CreatePaymentDevCommand(
                request.getUserId(),
                request.getPayment(),
                request.getReference()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreatePaymentDevMessage(id);
    }
}
