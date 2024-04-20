package com.kynsof.treatments.application.query.exam.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.ExamDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ExamResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private String type;
    private String result;
    private Date datePerformed;
    private Double price;

    public ExamResponse(ExamDto examDto) {
        this.id = examDto.getId();
        this.name = examDto.getName();
        this.description = examDto.getDescription();
        this.type = examDto.getType();
        this.result = examDto.getResult();
        this.datePerformed = examDto.getDatePerformed();
        this.price = examDto.getPrice();
    }

}
