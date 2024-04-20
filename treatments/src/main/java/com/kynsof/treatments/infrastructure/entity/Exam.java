package com.kynsof.treatments.infrastructure.entity;

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
    private String type;
    private String result;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePerformed;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_order_id")
    private ExamOrder examOrder;

    @PrePersist
    protected void onCreate() {
        datePerformed = new Date();
    }
}
