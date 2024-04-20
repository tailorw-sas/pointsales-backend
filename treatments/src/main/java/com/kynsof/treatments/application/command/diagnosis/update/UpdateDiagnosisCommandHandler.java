package com.kynsof.treatments.application.command.diagnosis.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import org.springframework.stereotype.Component;

@Component
public class UpdateDiagnosisCommandHandler implements ICommandHandler<UpdateDiagnosisCommand> {

    private final IDiagnosisService serviceImpl;

    public UpdateDiagnosisCommandHandler(IDiagnosisService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateDiagnosisCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Procedure ID cannot be null."));
        DiagnosisDto update = this.serviceImpl.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());
        UpdateIfNotNull.updateIfStringNotNull(update::setIcdCode, command.getIcdCode());

        serviceImpl.create(update);
    }
}
