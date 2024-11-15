package com.kynsof.identity.application.command.businessBalance.discount;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DiscountBusinessBalanceCommand implements ICommand {

    private boolean result;
    private final UUID businessId;
    private final Double balance;

    public DiscountBusinessBalanceCommand(UUID businessId, Double balance) {

        this.businessId = businessId;
        this.balance = balance;
    }

    public static DiscountBusinessBalanceCommand fromRequest(DiscountBusinessBalanceRequest request) {
        return new DiscountBusinessBalanceCommand(
                request.getBusinessId(),
                request.getBalance()

        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new DiscountBusinessBalanceMessage(result);
    }
}
