package com.kynsof.scheduled.application.command.receipt.create;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.domain.dto.PatientDto;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.PatientsServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ReceiptService;
import com.kynsof.scheduled.infrastructure.service.ScheduleServiceImpl;
import com.kynsof.scheduled.infrastructure.service.ServiceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateReceiptCommandHandler implements ICommandHandler<CreateReceiptCommand> {

    private final ReceiptService serviceImpl;
    private final PatientsServiceImpl servicePatientImpl;
    private final ScheduleServiceImpl serviceScheduleImpl;
    private final ServiceServiceImpl serviceServiceImpl;
    public CreateReceiptCommandHandler(ReceiptService serviceImpl, PatientsServiceImpl servicePatientImpl, 
                                       ScheduleServiceImpl serviceScheduleImpl, ServiceServiceImpl serviceServiceImpl) {
        this.serviceImpl = serviceImpl;
        this.servicePatientImpl = servicePatientImpl;
        this.serviceScheduleImpl = serviceScheduleImpl;
        this.serviceServiceImpl = serviceServiceImpl;
    }

    @Override
    public void handle(CreateReceiptCommand command) {
        PatientDto _patient = this.servicePatientImpl.findById(command.getUser());
        ScheduleDto _schedule = this.serviceScheduleImpl.findById(command.getSchedule());
        ServiceDto _service = this.serviceServiceImpl.findById(command.getService());

        serviceImpl.create(new ReceiptDto(
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
