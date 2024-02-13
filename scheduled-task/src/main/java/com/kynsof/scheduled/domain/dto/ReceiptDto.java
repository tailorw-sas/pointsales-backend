package com.kynsof.scheduled.domain.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiptDto implements Serializable {

    private UUID id;
    private Double price;
    private Boolean express;
    private String reasons;
    private PatientDto user;
    private ScheduleDto schedule;
    private ServiceDto service;
    private EStatusReceipt status;

}
