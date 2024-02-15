package com.kynsof.scheduled.application.command.receipt.create;

import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.domain.dto.PatientDto;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.domain.service.IPatientsService;
import com.kynsof.scheduled.domain.service.IReceiptService;
import com.kynsof.scheduled.domain.service.IScheduleService;
import com.kynsof.scheduled.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateReceiptCommandHandler implements ICommandHandler<CreateReceiptCommand> {

    private final IReceiptService service;
    private final IPatientsService servicePatient;
    private final IScheduleService serviceSchedule;
    private final IServiceService serviceService;

    public CreateReceiptCommandHandler(IReceiptService service, IPatientsService servicePatient, 
                                       IScheduleService serviceSchedule, IServiceService serviceService) {
        this.service = service;
        this.servicePatient = servicePatient;
        this.serviceSchedule = serviceSchedule;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(CreateReceiptCommand command) {
        PatientDto _patient = this.servicePatient.findById(command.getUser());
        ScheduleDto _schedule = this.serviceSchedule.findById(command.getSchedule());
        ServiceDto _service = this.serviceService.findById(command.getService());

        service.create(new ReceiptDto(
                command.getId(), 
                command.getPrice(), 
                command.getExpress(), 
                command.getReasons(), 
                _patient, 
                _schedule, 
                _service, 
                EStatusReceipt.PRE_RESERVE
        ));
    }
}
