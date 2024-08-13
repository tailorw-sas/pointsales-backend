package com.kynsof.calendar.application.command.receipt.createReceiptScheduled;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
public class CreateScheduleReceiptRequest {
    private UUID resource;
    private UUID business;
    private UUID service;
    private UUID user;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int stock;
    private String reason;
}