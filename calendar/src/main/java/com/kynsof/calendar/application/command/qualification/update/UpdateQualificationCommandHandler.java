package com.kynsof.calendar.application.command.qualification.update;

import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateQualificationCommandHandler implements ICommandHandler<UpdateQualificationCommand> {

    private final IQualificationService service;

    public UpdateQualificationCommandHandler(IQualificationService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateQualificationCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule(command.getId(), "id", "Qualification ID cannot be null."));

        QualificationDto update = this.service.findById(command.getId());
        UpdateIfNotNull.updateIfNotNull(update::setDescription, command.getDescription());
        update.setStatus(command.getStatus());

        service.update(update);
    }
}
