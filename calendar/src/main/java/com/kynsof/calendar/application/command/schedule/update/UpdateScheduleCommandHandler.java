package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleCommandHandler implements ICommandHandler<UpdateScheduleCommand> {

    private final IScheduleService scheduleService;

    public UpdateScheduleCommandHandler( IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void handle(UpdateScheduleCommand command) {
        ScheduleDto scheduleDto = scheduleService.findById(command.getId());
        scheduleDto.setStartTime(command.getStartTime());
        scheduleDto.setEndingTime(command.getEndingTime());
        scheduleDto.setDate(command.getDate());
        scheduleDto.setStock(command.getStock());
        scheduleService.update(scheduleDto);
    }
}
