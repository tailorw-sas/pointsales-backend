package com.kynsof.calendar.application.command.turn.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTurnRequest {

    private UUID doctor;
    private UUID service;
    private UUID business;
    private String identification;
}