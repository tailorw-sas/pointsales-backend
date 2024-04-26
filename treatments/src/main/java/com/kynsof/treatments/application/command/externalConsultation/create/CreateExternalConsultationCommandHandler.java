package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.service.IDoctorService;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CreateExternalConsultationCommandHandler implements ICommandHandler<CreateExternalConsultationCommand> {

    private final IExternalConsultationService externalConsultationService;
    private final IPatientsService patientsService;
    private final IDoctorService doctorService;
    private final IExamOrderService examOrderService;

    public CreateExternalConsultationCommandHandler(IExternalConsultationService externalConsultationService, IPatientsService patientsService, IDoctorService doctorService, IExamOrderService examOrderService) {
        this.externalConsultationService = externalConsultationService;
        this.patientsService = patientsService;
        this.doctorService = doctorService;
        this.examOrderService = examOrderService;
    }

    @Override
    public void handle(CreateExternalConsultationCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        DoctorDto doctorDto = doctorService.findById(command.getDoctorId());
        ExamOrderDto examOrderDto = null;
        if (command.getExamOrder() != null) {
            try {
                examOrderDto = this.examOrderService.findById(command.getExamOrder());
            } catch (Exception e) {
            }
        }

        UUID id = externalConsultationService.create(new ExternalConsultationDto(
                UUID.randomUUID(),
                patientDto,
                doctorDto,
                new Date(),
                command.getConsultationReason(),
                command.getMedicalHistory(),
                command.getPhysicalExam(),
                command.getObservations(),
                examOrderDto
        ));
        command.setId(id);
    }
}
