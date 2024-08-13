package com.kynsof.calendar.infrastructure.service.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewServiceMessage {
    private String shift;
    private String service;
    private String local;
    private String queueId;
}
