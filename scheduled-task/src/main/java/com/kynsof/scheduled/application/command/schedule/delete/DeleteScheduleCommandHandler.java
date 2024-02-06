package com.kynsof.scheduled.application.command.schedule.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteScheduleCommandHandler implements ICommandHandler<ScheduleDeleteCommand> {

    private final ScheduleServiceImpl serviceImpl;

    public DeleteScheduleCommandHandler(ScheduleServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(ScheduleDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
