package com.kynsof.calendar.application.command.receipt.confirmPayment;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPaymentReceiptCommandHandler implements ICommandHandler<ConfirmPaymentReceiptCommand> {

    private final IReceiptService service;
    private final IPatientsService servicePatient;
    private final IScheduleService serviceSchedule;
    private final IServiceService serviceService;

    public ConfirmPaymentReceiptCommandHandler(IReceiptService service, IPatientsService servicePatient,
                                               IScheduleService serviceSchedule, IServiceService serviceService) {
        this.service = service;
        this.servicePatient = servicePatient;
        this.serviceSchedule = serviceSchedule;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(ConfirmPaymentReceiptCommand command) {
        PatientDto _patient = this.servicePatient.findById(command.getUserId());
        ScheduleDto _schedule = this.serviceSchedule.findById(command.getScheduleId());
        ServiceDto _service = this.serviceService.findById(command.getServiceId());
        ReceiptDto _receipt = this.service.findById(command.getReceiptId());
           _receipt.setStatus(command.getStatus());

        service.update(
               _receipt, command.getScheduleId(),command.getServiceId(), command.getStatus(), _service.getNormalAppointmentPrice(),
                _receipt.getExpress(),_receipt.getReasons());
    }
}
