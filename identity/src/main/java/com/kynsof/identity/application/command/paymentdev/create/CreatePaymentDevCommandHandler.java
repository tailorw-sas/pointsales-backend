package com.kynsof.identity.application.command.paymentdev.create;

import com.kynsof.identity.domain.dto.PaymentDevDto;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.domain.rules.paymentdev.ModuleReferenceMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePaymentDevCommandHandler implements ICommandHandler<CreatePaymentDevCommand> {

    private final IPaymentDevService paymentDevService;
    private final IUserSystemService userSystemService;

    @Autowired
    public CreatePaymentDevCommandHandler(IPaymentDevService paymentDevService, IUserSystemService userSystemService) {
        this.paymentDevService = paymentDevService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreatePaymentDevCommand command) {

        RulesChecker.checkRule(new ModuleReferenceMustBeUniqueRule(this.paymentDevService, command.getReference(), command.getId()));
        UserSystemDto userSystemDto = this.userSystemService.findById(command.getUserId());
        PaymentDevDto paymentDevDto = new PaymentDevDto(
                command.getId(),
                userSystemDto,
                command.getPayment(),
                command.getReference());

        this.paymentDevService.create(paymentDevDto);

    }
}
