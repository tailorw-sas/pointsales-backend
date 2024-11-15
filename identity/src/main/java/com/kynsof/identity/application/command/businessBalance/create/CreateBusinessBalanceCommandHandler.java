package com.kynsof.identity.application.command.businessBalance.create;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessBalanceCommandHandler implements ICommandHandler<CreateBusinessBalanceCommand> {

    private final IBusinessService service;
    public CreateBusinessBalanceCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateBusinessBalanceCommand command) {
        BusinessDto businessDto = this.service.findById(command.getBusinessId());
        businessDto.setBalance(businessDto.getBalance() + command.getBalance());
        service.update(businessDto);
        command.setResult(true);
    }
}
