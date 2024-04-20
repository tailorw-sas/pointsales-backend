package com.kynsof.treatments.application.command.exam.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExamRequest {
    private String name;
    private String description;
    private String type;
    private String result;
    private Double price;
}
