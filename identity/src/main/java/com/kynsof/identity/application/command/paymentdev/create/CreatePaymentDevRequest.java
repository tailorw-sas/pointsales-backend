package com.kynsof.identity.application.command.paymentdev.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDevRequest {

    private UUID userId;
    private Double payment;
    private String reference;
}