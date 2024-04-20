package com.kynsof.treatments.application.command.exam.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.service.IExamService;
import org.springframework.stereotype.Component;

@Component
public class UpdateExamCommandHandler implements ICommandHandler<UpdateExamCommand> {

    private final IExamService serviceImpl;

    public UpdateExamCommandHandler(IExamService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateExamCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Exam ID cannot be null."));
        ExamDto update = this.serviceImpl.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());
        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        UpdateIfNotNull.updateIfStringNotNull(update::setResult, command.getResult());
        UpdateIfNotNull.updateIfStringNotNull(update::setType, command.getType());
        update.setPrice(command.getPrice());

        serviceImpl.create(update);
    }
}
