package com.kynsof.treatments.application.command.examOrder.create;

import com.kynsof.treatments.domain.bus.command.ICommand;
import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateExamOrderCommand implements ICommand {
    private UUID id;
    private String reason;
    private UUID patientId;
    private List<ExamRequest> exams;


    public CreateExamOrderCommand(UUID patientId, String reason, List<ExamRequest> examRequests) {

        this.patientId = patientId;
        this.reason = reason;
        this.exams = examRequests;
    }

    public static CreateExamOrderCommand fromRequest(CreateExamOrderRequest request) {
        return new CreateExamOrderCommand(request.getPatientId(), request.getReason(),
                request.getExams());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateExamOrderMessage(id);
    }
}
