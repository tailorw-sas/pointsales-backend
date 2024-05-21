package com.kynsof.identity.application.command.paymentdev.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentDevRequest {

    private Double payment;
    private String reference;
}