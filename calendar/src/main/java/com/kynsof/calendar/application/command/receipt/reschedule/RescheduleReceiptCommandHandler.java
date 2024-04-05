package com.kynsof.calendar.application.command.receipt.reschedule;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class RescheduleReceiptCommandHandler implements ICommandHandler<RescheduleReceiptCommand> {

    private final IReceiptService service;
    private final IScheduleService scheduleService;

    public RescheduleReceiptCommandHandler(IReceiptService service, IScheduleService scheduleService) {
        this.service = service;
        this.scheduleService = scheduleService;
    }

    @Override
    public void handle(RescheduleReceiptCommand command) {
        ReceiptDto _receipt = this.service.findById(command.getReceiptId());
        _receipt.setStatus(EStatusReceipt.CANCEL);
        ScheduleDto _sSchedule = this.scheduleService.findById(_receipt.getSchedule().getId());
        _sSchedule.setStock(_sSchedule.getStock() + 1);
        ScheduleDto _newSchedule = this.scheduleService.findById(command.getNewScheduledId());
        _newSchedule.setStock(_newSchedule.getStock()-1);
        scheduleService.update(_sSchedule);
        scheduleService.update(_newSchedule);
        service.update(_receipt);
    }
}
