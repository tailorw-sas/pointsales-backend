package com.kynsof.calendar.application.command.receipt.create;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

        if (_schedule.getStock() == 0) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_IS_NOT_AVAIBLE, "The selected schedule is not available.");
        }
        //TODO hacer un servicio que actualice el stock nada mas yannier
      //  _schedule.setStock(_schedule.getStock() - 1);
      //  this.serviceSchedule.update(_schedule);
       ReceiptDto receiptDto = new ReceiptDto(
                UUID.randomUUID(),
                command.getPrice(),
                command.getExpress(),
                command.getReasons(),
                _patient,
                _schedule,
                _service,
                EStatusReceipt.PRE_RESERVE

        );
        receiptDto.setIpAddressCreate(command.getIpAddress());
        receiptDto.setUserAgentCreate(command.getUserAgent());

        UUID id = service.create(receiptDto);
        command.setId(id);
    }
}
