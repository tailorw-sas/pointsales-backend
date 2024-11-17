package com.kynsof.treatments.application.command.businessProcedure.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BusinessProcedurePriceRequest {
    private UUID procedure;
    private double price;
    private String code;
}
