package com.kynsof.calendar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDto implements Serializable {
    private UUID id;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String logo;
}
