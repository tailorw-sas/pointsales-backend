package com.kynsof.treatments.application.query.examOrder.getall;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ExamOrderResponse implements IResponse {
    private UUID id;
    private String reason;
    private String status;
    private Double totalPrice;
    private Date orderDate;
    private PatientDto patient;
    private List<ExamDto> exams;


    public ExamOrderResponse(ExamOrderDto dto) {
        this.id = dto.getId();
        this.patient = dto.getPatient();
        this.reason = dto.getReason();
        this.status = dto.getStatus();
        this.totalPrice = dto.getTotalPrice();
        this.orderDate = dto.getOrderDate();
        this.exams = dto.getExams();
    }

}