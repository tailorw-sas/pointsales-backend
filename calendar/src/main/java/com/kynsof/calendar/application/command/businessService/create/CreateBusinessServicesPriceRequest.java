package com.kynsof.calendar.application.command.businessService.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessServicesPriceRequest {
    private UUID service;
    private double price;
}
