package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.walletTransaction.create.CreateWalletTransactionCommand;
import com.kynsof.identity.application.command.walletTransaction.create.CreateWalletTransactionMessage;
import com.kynsof.identity.application.command.walletTransaction.create.CreateWalletTransactionRequest;
import com.kynsof.identity.application.query.wallet.getByCustomerId.FindByCustomerIdQuery;
import com.kynsof.identity.application.query.wallet.getByCustomerId.WalletResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final IMediator mediator;

    public CustomerController(IMediator mediator) {

        this.mediator = mediator;
    }



    @PostMapping("/wallet/balance")
    public ResponseEntity<?> createWalletBalance(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateWalletTransactionRequest request) {
        String userId = jwt.getClaim("sub");
        CreateWalletTransactionCommand createCommand = CreateWalletTransactionCommand.fromRequest(UUID.fromString(userId), request);
        CreateWalletTransactionMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/wallet/balance")
    public ResponseEntity<?> getWalletBalance(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaim("sub");
        FindByCustomerIdQuery createCommand = new FindByCustomerIdQuery(UUID.fromString(userId));
        WalletResponse response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }



}
