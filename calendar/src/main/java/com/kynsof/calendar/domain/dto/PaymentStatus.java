package com.kynsof.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentStatus {
    private String requestId;
    private String status;
}
