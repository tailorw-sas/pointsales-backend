package com.kynsof.calendar.application.command.receipt.updateStatus;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.infrastructure.service.BusinessBalanceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateStatusReceiptCommandHandler implements ICommandHandler<UpdateStatusReceiptCommand> {

    private final IReceiptService receiptService;
    private final IScheduleService serviceSchedule;
    private final BusinessBalanceService businessBalanceService;

    public UpdateStatusReceiptCommandHandler(IReceiptService service, IScheduleService scheduleService, BusinessBalanceService businessBalanceService) {
        this.receiptService = service;
        this.serviceSchedule = scheduleService;
        this.businessBalanceService = businessBalanceService;
    }

    @Override
    public void handle(UpdateStatusReceiptCommand command) {
        ReceiptDto receiptDto = this.receiptService.findById(command.getId());
        receiptDto.setStatus(command.getStatus());

        ScheduleDto _schedule = serviceSchedule.findById(receiptDto.getSchedule().getId());
        //_schedule.setStatus(EStatusSchedule.ATTENDED);

        if (command.getStatus().equals(EStatusReceipt.CANCEL)) {
            receiptDto.setStatus(EStatusReceipt.CANCEL);
            cleanStock(_schedule);

        }
        if (command.getStatus().equals(EStatusReceipt.REJECTED)) {
            cleanStock(_schedule);
            receiptDto.setStatus(EStatusReceipt.REJECTED);
        }

        if (command.getStatus().equals(EStatusReceipt.CONFIRMED)){
            receiptDto.setStatus(EStatusReceipt.CONFIRMED);
            businessBalanceService.discountBusinessBalance(receiptDto.getSchedule().getBusiness().getId(), 0.25);
        }


        receiptService.update(receiptDto);
        serviceSchedule.update(_schedule);
    }

    private void cleanStock(ScheduleDto scheduleDto) {
        scheduleDto.setStock(scheduleDto.getStock() + 1);
        scheduleDto.setStatus(EStatusSchedule.AVAILABLE);
        serviceSchedule.update(scheduleDto);
    }
}
