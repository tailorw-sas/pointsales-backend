package com.kynsof.treatments.application.command.exam.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExamRequest {
    private String name;
    private String description;
    private String type;
    private String result;
    private Double price;
}
