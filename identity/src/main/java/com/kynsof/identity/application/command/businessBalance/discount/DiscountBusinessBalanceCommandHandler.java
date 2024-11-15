package com.kynsof.identity.application.command.businessBalance.discount;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DiscountBusinessBalanceCommandHandler implements ICommandHandler<DiscountBusinessBalanceCommand> {

    private final IBusinessService service;
    public DiscountBusinessBalanceCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(DiscountBusinessBalanceCommand command) {
        BusinessDto businessDto = this.service.findById(command.getBusinessId());
        if (businessDto.getBalance() <= 0) {
            throw new IllegalArgumentException("Cannot apply discount: Business balance is zero or negative.");
        }
        businessDto.setBalance(businessDto.getBalance() - command.getBalance());
        service.update(businessDto);
    }
}
