package com.kynsof.identity.application.command.paymentdev.create;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDevRequest {

    private UUID userId;
    private Double payment;
    private String reference;
}