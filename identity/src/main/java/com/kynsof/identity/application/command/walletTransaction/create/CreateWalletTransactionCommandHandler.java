package com.kynsof.identity.application.command.walletTransaction.create;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.dto.WalletDto;
import com.kynsof.identity.domain.dto.WalletTransactionDto;
import com.kynsof.identity.domain.dto.enumType.TransactionType;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IWalletService;
import com.kynsof.identity.domain.interfaces.service.IWalletTransactionService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateWalletTransactionCommandHandler implements ICommandHandler<CreateWalletTransactionCommand> {

    private final IUserSystemService customerService;
    private final IWalletTransactionService walletTransactionService;
    private final IWalletService walletService;


    public CreateWalletTransactionCommandHandler(IUserSystemService service, IWalletTransactionService walletTransactionService, IWalletService walletService) {
        this.customerService = service;
        this.walletTransactionService = walletTransactionService;
        this.walletService = walletService;
    }

    @Override
    public void handle(CreateWalletTransactionCommand command) {
        UserSystemDto customerDto = customerService.findById(command.getCustomerId());

        WalletDto walletDto = walletService.findByCustomerId(customerDto.getId());
        if (walletDto == null) {

            WalletDto value = new WalletDto(UUID.randomUUID(), command.getAmount(), customerDto, null);
            UUID id = walletService.create(value);
            value.setId(id);
            WalletTransactionDto walletTransactionDto = new WalletTransactionDto();
            walletTransactionDto.setId(UUID.randomUUID());
            walletTransactionDto.setWalletDto(value);
            walletTransactionDto.setWalletId(value.getId());
            walletTransactionDto.setAmount(command.getAmount());
            walletTransactionDto.setType(TransactionType.DEPOSIT);
            walletTransactionDto.setTransactionDate(LocalDateTime.now());
            walletTransactionDto.setDescription(command.getDescription());
            walletTransactionDto.setAuthorizationCode(command.getAuthorizationCode());
            walletTransactionDto.setRequestId(command.getRequestId());
            UUID result = walletTransactionService.create(walletTransactionDto);
            command.setId(result);

        } else {
            walletDto.setBalance(walletDto.getBalance().subtract(command.getAmount()));
            WalletTransactionDto walletTransactionDto = new WalletTransactionDto();
            walletTransactionDto.setId(UUID.randomUUID());
            walletTransactionDto.setWalletDto(walletDto);
            walletTransactionDto.setWalletId(walletDto.getId());
            walletTransactionDto.setAmount(command.getAmount());
            walletTransactionDto.setType(TransactionType.DEPOSIT);
            walletTransactionDto.setTransactionDate(LocalDateTime.now());
            walletTransactionDto.setDescription(command.getDescription());
            walletTransactionDto.setAuthorizationCode(command.getAuthorizationCode());
            walletTransactionDto.setRequestId(command.getRequestId());
            UUID result = walletTransactionService.create(walletTransactionDto);
            command.setId(result);
        }

    }
}