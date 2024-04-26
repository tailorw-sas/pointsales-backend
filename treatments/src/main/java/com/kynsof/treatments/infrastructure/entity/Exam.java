package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Exam {
    @Id
    private UUID id;

    private String name;
    private String description;
    private MedicalExamCategory type;
    private String result;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePerformed;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_order_id")
    private ExamOrder examOrder;

    @PrePersist
    protected void onCreate() {
        datePerformed = new Date();
    }

    public Exam(ExamDto examDto) {
        this.id = examDto.getId();
        this.name = examDto.getName();
        this.description = examDto.getDescription();
        this.type = examDto.getType();
        this.result = examDto.getResult();
        this.price = examDto.getPrice();
    }

    public ExamDto toAggregate() {
        return new ExamDto(id, name, description, type, result, datePerformed, price);
    }

}
