package com.kynsof.identity.application.command.paymentdev.update;

import com.kynsof.identity.domain.dto.PaymentDevDto;
import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.identity.domain.rules.paymentdev.ModuleReferenceMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.ConsumerUpdate;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdatePaymentDevCommandHandler implements ICommandHandler<UpdatePaymentDevCommand> {

    private final IPaymentDevService service;

    public UpdatePaymentDevCommandHandler(IPaymentDevService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdatePaymentDevCommand command) {

        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "PaymentDev ID cannot be null."));

        PaymentDevDto paymentDev = this.service.findById(command.getId());

        ConsumerUpdate update = new ConsumerUpdate();
        
        if (UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(paymentDev::setReference, command.getReference(), paymentDev.getReference(), update::setUpdate)) {
            RulesChecker.checkRule(new ModuleReferenceMustBeUniqueRule(this.service, command.getReference(), command.getId()));
        }
        UpdateIfNotNull.updateDouble(paymentDev::setPayment, command.getPayment(), paymentDev.getPayment(), update::setUpdate);

        if (update.getUpdate() > 0) {
            this.service.update(paymentDev);
        }

    }
}
