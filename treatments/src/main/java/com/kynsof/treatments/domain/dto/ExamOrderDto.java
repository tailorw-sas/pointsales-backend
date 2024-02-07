package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ExamOrderDto {
    private UUID id;
    private String reason;
    private String status;
    private Double totalPrice;
    private Date orderDate;
    private PatientDto patient;
    private List<ExamDto> exams;
}