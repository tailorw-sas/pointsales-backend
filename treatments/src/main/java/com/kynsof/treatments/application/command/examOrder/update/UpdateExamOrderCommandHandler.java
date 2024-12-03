package com.kynsof.treatments.application.command.examOrder.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.domain.service.IExamService;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UpdateExamOrderCommandHandler implements ICommandHandler<UpdateExamOrderCommand> {

    private final IPatientsService patientsService;
    private final IExamOrderService examOrderService;
    private final IExamService examService;

    public UpdateExamOrderCommandHandler(IPatientsService patientsService, IExamOrderService examOrderService, IExamService examService) {
        this.patientsService = patientsService;
        this.examOrderService = examOrderService;
        this.examService = examService;
    }

    @Override
    public void handle(UpdateExamOrderCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Exam Order ID cannot be null."));
        ExamOrderDto update = this.examOrderService.findById(command.getId());
        if (!update.getExams().isEmpty()) {
            for (ExamDto exam : update.getExams()) {
                this.examService.delete(exam);
            }
        }
        update.setExams(new ArrayList<>());

        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getPatientId(), "id", "Patient ID cannot be null."));
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        update.setPatient(patientDto);

        List<ExamDto> examDtoList = command.getExams().stream()
                .map(examRequest -> new ExamDto(
                UUID.randomUUID(),
                examRequest.getName(),
                examRequest.getDescription(),
                examRequest.getType(),
                "",
                new Date(),
                examRequest.getCode()
        ))
                .collect(Collectors.toList());
        update.setExams(examDtoList);

        UpdateIfNotNull.updateIfStringNotNull(update::setReason, command.getReason());
        this.examOrderService.update(update);
    }
}
