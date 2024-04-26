package com.kynsof.identity.application.command.walletTransaction.create;

import com.kynsof.identity.domain.dto.enumType.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateWalletTransactionRequest {
    private UUID walletId;
    private BigDecimal amount;
    private TransactionType type;
    private String description;
    private String requestId;
    private String authorizationCode;
}
