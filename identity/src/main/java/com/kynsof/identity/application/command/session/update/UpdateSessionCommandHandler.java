package com.kynsof.identity.application.command.session.update;

import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.interfaces.service.ISessionService;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSessionCommandHandler implements ICommandHandler<UpdateSessionCommand> {

    private final ISessionService sessionService;

    @Autowired
    private IUserSystemService userSystemService;

    @Autowired
    private IBusinessService businessService;

    public UpdateSessionCommandHandler(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void handle(UpdateSessionCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(),
                "id", "Session ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getUserSystemId(),
                "userSystem", "UserSystem ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusinessId(),
                "business", "Business ID cannot be null."));

        SessionDto updateSession = this.sessionService.findById(command.getId());
        UserSystemDto userSystem = this.userSystemService.findById(command.getUserSystemId());
        BusinessDto business = this.businessService.findById(command.getBusinessId());

        UpdateIfNotNull.updateIfNotNull(updateSession::setStatus, command.getStatus());

        updateSession.setUserSystemDto(userSystem);
        updateSession.setBusinessDto(business);

        this.sessionService.update(updateSession);
    }
}


