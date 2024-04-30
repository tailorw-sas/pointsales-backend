package com.kynsof.treatments.application.command.examOrder.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateExamOrderCommand implements ICommand {

    private UUID id;
    private String reason;
    private UUID patientId;
    private List<UpdateExamRequest> exams;

    public UpdateExamOrderCommand(UUID id, UUID patientId, String reason, List<UpdateExamRequest> examRequests) {
        this.id = id;
        this.patientId = patientId;
        this.reason = reason;
        this.exams = examRequests;
    }

    public static UpdateExamOrderCommand fromRequest(UpdateExamOrderRequest request, UUID id) {
        return new UpdateExamOrderCommand(id, request.getPatient(), request.getReason(),
                request.getExams());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateExamOrderMessage(id);
    }
}
