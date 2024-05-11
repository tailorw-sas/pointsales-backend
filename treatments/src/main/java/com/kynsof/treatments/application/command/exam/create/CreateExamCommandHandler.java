package com.kynsof.treatments.application.command.exam.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.service.IExamService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateExamCommandHandler implements ICommandHandler<CreateExamCommand> {

    private final IExamService serviceImpl;

    public CreateExamCommandHandler(IExamService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateExamCommand command) {
        ExamDto create = new ExamDto(
                command.getId(), 
                command.getName(), 
                command.getDescription(), 
                command.getType(), 
                command.getResult(), 
                command.getPrice()
        );
        create.setCode(command.getCode());

        UUID id = serviceImpl.create(create);
        command.setId(id);
    }
}
