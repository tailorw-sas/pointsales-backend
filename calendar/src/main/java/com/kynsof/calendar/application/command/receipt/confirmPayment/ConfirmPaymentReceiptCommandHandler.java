package com.kynsof.calendar.application.command.receipt.confirmPayment;

import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.infrastructure.service.kafka.producer.ProducerGenerateReportEventService;
import com.kynsof.share.core.application.payment.domain.placeToPlay.response.TransactionsState;
import com.kynsof.share.core.application.payment.domain.service.IPaymentServiceClient;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPaymentReceiptCommandHandler implements ICommandHandler<ConfirmPaymentReceiptCommand> {

    private final IReceiptService service;
    private final IPaymentServiceClient paymentServiceClient;
    private final ProducerGenerateReportEventService producerGenerateReportEventService;

    public ConfirmPaymentReceiptCommandHandler(IReceiptService service, IPaymentServiceClient paymentServiceClient,
                                               ProducerGenerateReportEventService producerGenerateReportEventService) {
        this.service = service;
        this.paymentServiceClient = paymentServiceClient;
        this.producerGenerateReportEventService = producerGenerateReportEventService;
    }

    @Override
    public void handle(ConfirmPaymentReceiptCommand command) {
        TransactionsState transactionsState = paymentServiceClient.getTransactionsState(Integer.parseInt(command.getRequestId()));
        if(transactionsState == null || !transactionsState.getValue().getStatus().getStatus().equals("APPROVED")) {
            throw new BusinessException(DomainErrorMessage.PAYMENT_NOT_FOUND, DomainErrorMessage.PAYMENT_NOT_FOUND.toString());
        }
        ReceiptDto _receipt = this.service.findById(command.getReceiptId());
        //  PatientDto _patient = this.servicePatient.findById(command.getUserId());
        //   ScheduleDto _schedule = this.serviceSchedule.findById(command.getScheduleId());
        //   ServiceDto _service = this.serviceService.findByIds(command.getServiceId());

        _receipt.setAuthorizationCode(transactionsState.getValue().getAuthorization());
        _receipt.setRequestId(command.getRequestId());
        _receipt.setReference(transactionsState.getValue().getReference());
        _receipt.setSessionId(command.getSessionId());
        _receipt.setIpAddressPayment(command.getIpAddress());
        _receipt.setUserAgentPayment(command.getUserAgent());

        if (transactionsState.getValue().getStatus().getStatus().equals(EStatusReceipt.APPROVED.toString())) {
            _receipt.setStatus(command.getStatus());
            //  _schedule.setStatus(EStatusSchedule.RESERVED);
            //  this.serviceSchedule.update(_schedule);
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("logo", _schedule.getBusiness().getLogo());
//            parameters.put("cita", _receipt.getId().toString());
//            parameters.put("nombres", _patient.getName() + " " + _patient.getLastName());
//            parameters.put("identidad", _patient.getIdentification());
//            parameters.put("fecha", _schedule.getDate());
//            parameters.put("hora", _schedule.getStartTime());
//            parameters.put("servicio", _service.getName());
//            parameters.put("tipo", _service.getType().getName());
//            parameters.put("direccion", _schedule.getBusiness().getAddress());
//            parameters.put("lugar", _schedule.getBusiness().getName());
//            parameters.put("fecha_registro", LocalDateTime.now());
//            parameters.put("URL_QR", "http://d3ksvzqyx4up5m.cloudfront.net/Ttt_2024-03-14_19-03-33.png");
//
//            GenerateReportKafka report = new GenerateReportKafka();
//            report.setParameters(parameters);
//            report.setEmail(_patient.getEmail());
//            report.setJasperReportCode("7777");
//
//            this.producerGenerateReportEventService.create(report);
        }

        if (transactionsState.getValue().getStatus().getStatus().equals(EStatusReceipt.CANCEL.toString())) {
            //TO DO
            //Liverar el stock
            //Enviar Correo de cancelado
            _receipt.setStatus(command.getStatus());
            _receipt.getSchedule().setStock(_receipt.getSchedule().getStock() - 1);

            //  cleanStock(_schedule);

        }
        if (transactionsState.getValue().getStatus().getStatus().equals(EStatusReceipt.REJECTED.toString())) {
            _receipt.getSchedule().setStock(_receipt.getSchedule().getStock() - 1);
            //TO DO
            //Validar el estado del pago, si el estado es pendiente de pago o pago hacer el proceso de confirmado ,sino cambiar el estado
            //  cleanStock(_schedule);

            //Enviar Correo de cancelado
            _receipt.setStatus(command.getStatus());

        }
        if (command.getStatus().equals(EStatusReceipt.PENDING)) {
            //TODO

            _receipt.setStatus(command.getStatus());
        }

        service.update(_receipt);
    }

}
