package com.kynsof.treatments.application.command.examOrder.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreateExamOrderCommandHandler implements ICommandHandler<CreateExamOrderCommand> {

    private final IPatientsService patientsService;
    private final IExamOrderService examOrderService;

    public CreateExamOrderCommandHandler(IPatientsService patientsService, IExamOrderService examOrderService) {
        this.patientsService = patientsService;
        this.examOrderService = examOrderService;
    }

    @Override
    public void handle(CreateExamOrderCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        // DoctorDto doctorDto = doctorService.findById(command.getDoctorId());

        List<ExamDto> examDtoList = command.getExams().stream()
                .map(examRequest -> new ExamDto(
                UUID.randomUUID(),
                examRequest.getName(),
                examRequest.getDescription(),
                examRequest.getType(),
                "",
                new Date(),
                examRequest.getPrice(),
                examRequest.getCode()
        ))
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
