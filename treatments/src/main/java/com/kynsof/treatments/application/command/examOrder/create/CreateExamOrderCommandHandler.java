package com.kynsof.treatments.application.command.examOrder.create;

import com.kynsof.treatments.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.*;
import com.kynsof.treatments.domain.service.IDoctorService;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateExamOrderCommandHandler implements ICommandHandler<CreateExamOrderCommand> {

    private final IExternalConsultationService externalConsultationService;
    private final IPatientsService patientsService;
    private final IDoctorService doctorService;
    private final IExamOrderService examOrderService;

    public CreateExamOrderCommandHandler(IExternalConsultationService externalConsultationService, IPatientsService patientsService, IDoctorService doctorService, IExamOrderService examOrderService) {
        this.externalConsultationService = externalConsultationService;
        this.patientsService = patientsService;
        this.doctorService = doctorService;
        this.examOrderService = examOrderService;
    }

    @Override
    public void handle(CreateExamOrderCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
       // DoctorDto doctorDto = doctorService.findById(command.getDoctorId());

        List<ExamDto>examDtoList= command.getExams().stream()
                .map(examRequest -> new ExamDto(
                        UUID.randomUUID(),
                        examRequest.getName(),
                        examRequest.getDescription(),
                        examRequest.getType(),
                        "",
                        new Date(),
                        examRequest.getPrice()))
                .collect(Collectors.toList());


        UUID id = examOrderService.create(new ExamOrderDto(
                UUID.randomUUID(),
                command.getReason(),
                Status.ACTIVE.toString(),
               0.0,
                new Date(),
                patientDto,
                examDtoList
        ));
       command.setId(id);
    }
}
