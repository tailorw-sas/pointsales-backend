package com.kynsof.identity.application.command.session.create;

import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import com.kynsof.identity.domain.interfaces.service.ISessionService;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.stereotype.Component;

@Component
public class CreateSessionCommandHandler implements ICommandHandler<CreateSessionCommand> {

    private final ISessionService sessionService;
    private final IUserSystemService userSystemService;
    private final IBusinessService businessService;

    public CreateSessionCommandHandler(ISessionService sessionService,
                                       IUserSystemService userSystemService,
                                       IBusinessService businessService) {
        this.sessionService = sessionService;
        this.userSystemService = userSystemService;
        this.businessService = businessService;
    }

    @Override
    public void handle(CreateSessionCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getUserSystemId(),
                "userSystem", "UserSystem ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusinessId(),
                "business", "Business ID cannot be null."));

        UserSystemDto userSystem = this.userSystemService.findById(command.getUserSystemId());
        BusinessDto business = this.businessService.findById(command.getBusinessId());

        SessionDto create = new SessionDto(
                command.getId(),
                userSystem,
                ESessionStatus.ACTIVE,
                business
        );

        create.setCreateAt(ConfigureTimeZone.getTimeZone());
        sessionService.create(create);
    }
}