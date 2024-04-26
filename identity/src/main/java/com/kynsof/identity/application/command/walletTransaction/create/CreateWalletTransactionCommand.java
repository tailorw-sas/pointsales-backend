package com.kynsof.identity.application.command.walletTransaction.create;

import com.kynsof.identity.domain.dto.enumType.TransactionType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateWalletTransactionCommand implements ICommand {

    private UUID id;
    private final UUID walletId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final String description;
    private final String requestId;
    private final String authorizationCode;

    public CreateWalletTransactionCommand( UUID walletId, BigDecimal amount, TransactionType type, String description,
                                           String requestId, String authorizationCode) {
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.requestId = requestId;
        this.authorizationCode = authorizationCode;
    }


    public static CreateWalletTransactionCommand fromRequest(CreateWalletTransactionRequest request) {
        return new CreateWalletTransactionCommand(
                request.getWalletId(),
                request.getAmount(),
                request.getType(),
                request.getDescription(), 
                request.getRequestId(),
                request.getAuthorizationCode()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateWalletTransactionMessage(id);
    }
}
