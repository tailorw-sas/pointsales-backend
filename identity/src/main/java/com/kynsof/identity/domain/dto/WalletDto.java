package com.kynsof.identity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class WalletDto {
    private UUID id;
    private BigDecimal balance;
    private CustomerDto customer;
    private List<WalletTransactionDto> transactions;
}
