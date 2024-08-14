package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.application.query.service.ServicesResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessServicePriceResponse {
    private Double price;
    private ServicesResponse service;
}
