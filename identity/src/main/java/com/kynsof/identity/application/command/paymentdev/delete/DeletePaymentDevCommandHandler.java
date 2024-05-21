package com.kynsof.identity.application.command.paymentdev.delete;

import com.kynsof.identity.domain.dto.PaymentDevDto;
import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeletePaymentDevCommandHandler implements ICommandHandler<DeletePaymentDevCommand> {

    private final IPaymentDevService serviceImpl;

    public DeletePaymentDevCommandHandler(IPaymentDevService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeletePaymentDevCommand command) {

        PaymentDevDto delete = this.serviceImpl.findById(command.getId());
        serviceImpl.delete(delete);
    }

}
