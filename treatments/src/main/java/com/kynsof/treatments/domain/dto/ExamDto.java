package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExamDto {
    private UUID id;
    private String name;
    private String description;
    private String type;
    private String result;
    private Date datePerformed;
    private Double price;
}
