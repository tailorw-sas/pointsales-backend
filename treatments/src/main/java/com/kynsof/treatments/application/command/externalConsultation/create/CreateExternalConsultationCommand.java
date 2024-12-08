package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateExternalConsultationCommand implements ICommand {

    private UUID id;
    private UUID patientId;
    private UUID doctorId;
    private UUID businessId;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;
    private CreateExamOrderRequest examOrder;
    private final List<DiagnosisRequest> diagnosis;
    private final List<TreatmentRequest> treatments;
    private final String medicalSpeciality;

    public CreateExternalConsultationCommand(UUID patientId, UUID doctorId, String consultationReason,
                                             String medicalHistory, String physicalExam, String observations,
                                             CreateExamOrderRequest examOrder, List<DiagnosisRequest> diagnosis,
                                             List<TreatmentRequest> treatments, UUID businessId, String medicalSpeciality) {

        this.patientId = patientId;
        this.doctorId = doctorId;
        this.businessId = businessId;
        this.consultationReason = consultationReason;
        this.medicalHistory = medicalHistory;
        this.physicalExam = physicalExam;
        this.observations = observations;
        this.examOrder = examOrder;
        this.diagnosis = diagnosis;
        this.treatments = treatments;
        this.medicalSpeciality = medicalSpeciality;
    }

    public static CreateExternalConsultationCommand fromRequest(CreateExternalConsultationRequest request) {
        return new CreateExternalConsultationCommand(request.getPatient(), request.getDoctor(),
                request.getConsultationReason(), request.getMedicalHistory(), request.getPhysicalExam(),
                request.getObservations(), request.getExamOrder(),request.getDiagnosis() ,request.getTreatments(), 
                request.getBusiness(),request.getMedicalSpeciality() );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateExternalConsultationMessage(id);
    }
}
