package com.kynsof.calendar.application.command.receipt.updateStatus;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.share.core.application.payment.domain.service.IPaymentServiceClient;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateStatusReceiptCommandHandler implements ICommandHandler<UpdateStatusReceiptCommand> {

    private final IReceiptService service;
    private final IScheduleService serviceSchedule;
    private final IPaymentServiceClient paymentServiceClient;

    public UpdateStatusReceiptCommandHandler(IReceiptService service, IScheduleService scheduleService, IPaymentServiceClient paymentServiceClient) {
        this.service = service;
        this.serviceSchedule = scheduleService;
        this.paymentServiceClient = paymentServiceClient;
    }

    @Override
    public void handle(UpdateStatusReceiptCommand command) {
        ReceiptDto dto = this.service.findById(command.getId());
        dto.setStatus(command.getStatus());

        ScheduleDto _schedule = serviceSchedule.findById(dto.getSchedule().getId());


        if (command.getStatus().equals(EStatusReceipt.CANCEL)) {

            dto.setStatus(EStatusReceipt.CANCEL);
            cleanStock(_schedule);

        }
        if (command.getStatus().equals(EStatusReceipt.REJECTED)) {
            cleanStock(_schedule);
            dto.setStatus(EStatusReceipt.REJECTED);
        }
        service.update(dto);
    }

    private void cleanStock(ScheduleDto scheduleDto) {
        scheduleDto.setStock(scheduleDto.getStock() + 1);
        serviceSchedule.update(scheduleDto);
    }
}
