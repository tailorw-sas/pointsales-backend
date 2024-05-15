package com.kynsof.calendar.application.command.schedule.createGoogleStyle;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateScheduleByLoteGoogleStyleCommand implements ICommand {
    private UUID business;
    private UUID service;
    private UUID resource;
    private List<ScheduleRequest> days;

    private final IMediator mediator;

    public CreateScheduleByLoteGoogleStyleCommand(
            UUID idResource, 
            UUID idBusiness, 
            UUID serviceId,
            List<ScheduleRequest> days,
            IMediator mediator) {
        this.resource = idResource;
        this.business = idBusiness;
        this.service = serviceId;
        this.days = List.copyOf(days);
        this.mediator = mediator;
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateScheduleByLoteGoogleStyleMessage();
    }
}
