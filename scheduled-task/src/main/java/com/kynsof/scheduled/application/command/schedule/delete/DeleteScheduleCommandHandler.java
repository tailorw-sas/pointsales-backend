package com.kynsof.scheduled.application.command.schedule.delete;

import com.kynsof.scheduled.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteScheduleCommandHandler implements ICommandHandler<ScheduleDeleteCommand> {

    private final IScheduleService service;

    public DeleteScheduleCommandHandler(IScheduleService service) {
        this.service = service;
    }

    @Override
    public void handle(ScheduleDeleteCommand command) {

        service.delete(command.getId());
    }

}
