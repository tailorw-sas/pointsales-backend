package com.kynsof.identity.application.command.businessBalance.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessBalanceCommand implements ICommand {

    private boolean result;
    private final UUID businessId;
    private final Double balance;

    public CreateBusinessBalanceCommand(UUID businessId, Double balance) {
        this.setResult(Boolean.FALSE);
        this.businessId = businessId;
        this.balance = balance;
    }

    public static CreateBusinessBalanceCommand fromRequest(CreateBusinessBalanceRequest request) {
        return new CreateBusinessBalanceCommand(
                request.getBusinessId(),
                request.getBalance()

        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBusinessBalanceMessage(result);
    }
}
