package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ExamOrder {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String reason;
    private String status;
    private Double totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "examOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Exam> exams;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    @ManyToOne(optional = true)
    @JoinColumn(name = "consultation_id")
    private ExternalConsultation externalConsultation;

    public ExamOrder(ExamOrderDto dto) {
        this.id = dto.getId();
        this.reason = dto.getReason();
        this.status = dto.getStatus();
        this.patient = new Patients(dto.getPatient());
        this.exams = dto.getExams().stream().map(examDto -> {
            Exam exam = new Exam();
            exam.setExamOrder(this);
            exam.setDescription(examDto.getDescription());
            exam.setName(examDto.getName());
            exam.setPrice(examDto.getPrice());
            exam.setType(examDto.getType());
            exam.setResult(examDto.getResult());
            return exam;
        }).collect(Collectors.toList());
        this.recalculateTotalPrice();
    }

    public void recalculateTotalPrice() {
        totalPrice = exams.stream().mapToDouble(Exam::getPrice).sum();
    }

    @PrePersist
    protected void onCreate() {
        orderDate = new Date();
    }

    public ExamOrderDto toAggregate() {
      List<ExamDto> examDtoList = this.exams.stream().map(exam -> {
          ExamDto examDto = new ExamDto();
          examDto.setDescription(exam.getDescription());
          examDto.setName(exam.getName());
          examDto.setPrice(exam.getPrice());
          examDto.setType(exam.getType());
          examDto.setResult(exam.getResult());
         return examDto;
      }).collect(Collectors.toList());
        return new ExamOrderDto(this.id, this.reason, this.status,this.totalPrice, this.orderDate,
                this.patient.toAggregate(),examDtoList);
    }
}
