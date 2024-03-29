package com.kynsof.calendar.application.command.receipt.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
    private String requestId;
}
