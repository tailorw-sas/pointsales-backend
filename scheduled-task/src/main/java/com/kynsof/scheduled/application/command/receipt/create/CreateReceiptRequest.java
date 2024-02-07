package com.kynsof.scheduled.application.command.receipt.create;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateReceiptRequest {
    private Double price;
    private Boolean express;
    private String reasons;
    private UUID user;
    private UUID schedule;
    private UUID service;

}
