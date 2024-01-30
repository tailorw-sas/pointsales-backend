package com.kynsoft.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class ServiceInstanceDTO {

    private String serviceId;
    private String url;

}