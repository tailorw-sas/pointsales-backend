package com.kynsof.treatments.application.query.externalConsultation.getall;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.application.query.business.search.BusinessResponse;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.domain.dto.DoctorDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class ExternalConsultationResponse implements IResponse {

    private UUID id;
    private String referenceNumber;
    private PatientDto patient;
    private DoctorDto doctor;
    private Date consultationTime;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String medicalSpeciality;
    private List<DiagnosisExternalConsultationResponse> diagnoses;
    private List<TreatmentExternalConsultationResponse> treatments;
    private String observations;
    private ExamOrderResponse examOrder;
    private BusinessResponse business;

    public ExternalConsultationResponse(ExternalConsultationDto dto) {
        this.id = dto.getId();
        this.referenceNumber = dto.getReferenceNumber();
        this.patient = dto.getPatient();
        this.consultationReason = dto.getConsultationReason();
        this.medicalHistory = dto.getMedicalHistory();
        this.physicalExam = dto.getPhysicalExam();
        this.consultationTime = dto.getConsultationTime();
        this.doctor = dto.getDoctor();
        this.observations = dto.getObservations();
        this.medicalSpeciality = dto.getMedicalSpeciality();
        this.diagnoses = dto.getDiagnoses().stream()
                .map(DiagnosisExternalConsultationResponse::new)
                .collect(Collectors.toList());
        this.treatments = dto.getTreatments().stream()
                .map(TreatmentExternalConsultationResponse::new)
                .collect(Collectors.toList());
        this.examOrder = dto.getExamOrder() != null ? new ExamOrderResponse(dto.getExamOrder()) : null;
        this.business = dto.getBusiness() != null ? new BusinessResponse(dto.getBusiness()) : null;
    }

}
