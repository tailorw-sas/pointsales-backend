package com.kynsof.calendar.application.command.receipt.confirmPayment;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.application.payment.domain.placeToPlay.response.TransactionsState;
import com.kynsof.share.core.application.payment.domain.service.IPaymentServiceClient;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPaymentReceiptCommandHandler implements ICommandHandler<ConfirmPaymentReceiptCommand> {

    private final IReceiptService service;
    private final IPatientsService servicePatient;
    private final IScheduleService serviceSchedule;
    private final IServiceService serviceService;
    private final IPaymentServiceClient paymentServiceClient;

    public ConfirmPaymentReceiptCommandHandler(IReceiptService service, IPatientsService servicePatient,
                                               IScheduleService serviceSchedule, IServiceService serviceService, IPaymentServiceClient paymentServiceClient) {
        this.service = service;
        this.servicePatient = servicePatient;
        this.serviceSchedule = serviceSchedule;
        this.serviceService = serviceService;
        this.paymentServiceClient = paymentServiceClient;
    }

    @Override
    public void handle(ConfirmPaymentReceiptCommand command) {
        TransactionsState transactionsState = paymentServiceClient.getTransactionsState(Integer.parseInt(command.getRequestId()));
        ReceiptDto _receipt = this.service.findById(command.getReceiptId());
        PatientDto _patient = this.servicePatient.findById(command.getUserId());
        ScheduleDto _schedule = this.serviceSchedule.findById(command.getScheduleId());
        ServiceDto _service = this.serviceService.findById(command.getServiceId());

        _receipt.setAuthorizationCode(transactionsState.getValue().getAuthorization());
        _receipt.setRequestId(command.getRequestId());
        _receipt.setReference(transactionsState.getValue().getReference());
        _receipt.setSessionId(command.getSessionId());
        _receipt.setIpAddressPayment(command.getIpAddress());
        _receipt.setUserAgentPayment(command.getUserAgent());

        if (transactionsState.getValue().getStatus().getStatus().equals(EStatusReceipt.APPROVED.toString())){
            _receipt.setStatus(command.getStatus());
//TODO
            //Enviar correo
        }

        if(transactionsState.getValue().getStatus().getStatus().equals(EStatusReceipt.CANCEL.toString())){
            //TO DO
            //Liverar el stock
            //Enviar Correo de cancelado
            _receipt.setStatus(command.getStatus());
            cleanStock(_schedule);

        }
        if (transactionsState.getValue().getStatus().getStatus().equals(EStatusReceipt.REJECTED.toString())){
            //TO DO
            //Validar el estado del pago, si el estado es pendiente de pago o pago hacer el proceso de confirmado ,sino cambiar el estado
            cleanStock(_schedule);

            //Enviar Correo de cancelado
            _receipt.setStatus(command.getStatus());

        }
        if(command.getStatus().equals(EStatusReceipt.PENDING)){
            //TODO

            _receipt.setStatus(command.getStatus());
        }



        service.update( _receipt);
    }

    private void cleanStock(ScheduleDto scheduleDto){
        scheduleDto.setStock(scheduleDto.getStock()+1);
        serviceSchedule.update(scheduleDto);
    }
}
