package com.kynsof.treatments.application.command.procedure.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.service.IProcedureService;
import org.springframework.stereotype.Component;

@Component
public class UpdateProcedureCommandHandler implements ICommandHandler<UpdateProcedureCommand> {

    private final IProcedureService serviceImpl;

    public UpdateProcedureCommandHandler(IProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateProcedureCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Procedure ID cannot be null."));

        ProcedureDto update = this.serviceImpl.findById(command.getId());
        UpdateIfNotNull.updateIfStringNotNull(update::setCode, command.getCode());
        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());
        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        update.setPrice(command.getPrice());
        update.setType(command.getType());

        serviceImpl.update(update);
    }
}
