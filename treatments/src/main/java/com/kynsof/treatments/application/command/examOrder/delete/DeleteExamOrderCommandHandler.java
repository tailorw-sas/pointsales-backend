package com.kynsof.treatments.application.command.examOrder.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.service.IExamOrderService;
import org.springframework.stereotype.Component;

@Component
public class DeleteExamOrderCommandHandler implements ICommandHandler<ExamOrderDeleteCommand> {

    private final IExamOrderService serviceImpl;

    public DeleteExamOrderCommandHandler(IExamOrderService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(ExamOrderDeleteCommand command) {
        ExamOrderDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
