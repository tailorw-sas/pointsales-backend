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

    public ExamDto(UUID id, String name, String description, String type, String result, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.result = result;
        this.price = price;
    }

}
