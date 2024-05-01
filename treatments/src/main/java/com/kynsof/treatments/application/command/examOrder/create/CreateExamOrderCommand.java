package com.kynsof.treatments.application.command.examOrder.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateExamOrderCommand implements ICommand {

    private UUID id;
    private String reason;
    private UUID patientId;
    private List<ExamRequest> exams;
    private final UUID externalConsultation;

    public CreateExamOrderCommand(UUID patientId, String reason, List<ExamRequest> examRequests, UUID externalConsultation) {

        this.patientId = patientId;
        this.reason = reason;
        this.exams = examRequests;
        this.externalConsultation = externalConsultation;
    }

    public static CreateExamOrderCommand fromRequest(CreateExamOrderRequest request) {
        return new CreateExamOrderCommand(request.getPatient(), request.getReason(),
                request.getExams(), request.getExternalConsultation());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateExamOrderMessage(id);
    }
}
