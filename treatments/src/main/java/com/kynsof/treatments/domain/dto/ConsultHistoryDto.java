package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultHistoryDto {
    private UUID id;
    private String identification;
    private String observations;
    private String motive;
    private String physicalExamination;
    private int consultId;
    private String speciality;
    private String doctor;
    private Date consultDate;


}
