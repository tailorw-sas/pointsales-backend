package com.kynsof.scheduled.infrastructure.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.query.ReceiptResponse;
import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.domain.exception.BusinessException;
import com.kynsof.scheduled.domain.exception.DomainErrorMessage;
import com.kynsof.scheduled.domain.service.IReceiptService;
import com.kynsof.scheduled.infrastructure.command.ReceiptWriteDataJPARepository;
import com.kynsof.scheduled.infrastructure.entity.Receipt;
import com.kynsof.scheduled.infrastructure.entity.specifications.ReceiptSpecifications;
import com.kynsof.scheduled.infrastructure.query.ReceiptReadDataJPARepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService implements IReceiptService {

    @Autowired
    private ReceiptReadDataJPARepository receiptRepositoryQuery;

    @Autowired
    private ReceiptWriteDataJPARepository receiptRepositoryCommand;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    private ServiceServiceImpl serviceServiceImpl;

    @Override
    public PaginatedResponse findAll(Pageable pageable, String filter, UUID resource, UUID user, UUID service,
            UUID schedule, LocalDate date, LocalDate startDate, LocalDate endDate, EStatusReceipt status) {
        ReceiptSpecifications spec = new ReceiptSpecifications(filter, null, user, schedule, service, resource, startDate, endDate, date, status);
        Page<Receipt> data = receiptRepositoryQuery.findAll(spec, pageable);

        List<ReceiptResponse> objects = data.getContent().stream()
                .map(Receipt::toAggregate)
                .map(ReceiptResponse::new)
                .collect(Collectors.toList());

        return new PaginatedResponse(objects, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void create(ReceiptDto receipt) {

        ScheduleDto _schedule = receipt.getSchedule();
        _schedule.setStatus(EStatusSchedule.PRE_RESERVE);
        receipt.setSchedule(_schedule);

        receipt.setStatus(EStatusReceipt.PRE_RESERVE);
        this.receiptRepositoryCommand.save(new Receipt(receipt));

    }

    @Override
    public void delete(UUID id) {
        ReceiptDto _receipt = this.findById(id);
        this.changeState(_receipt, EStatusReceipt.CANCEL);
    }

    @Override
    public ReceiptDto findById(UUID id) {
        Optional<Receipt> object = this.receiptRepositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Receipt not found.");
    }

    private void changeServiceScheduleStatus(ReceiptDto receipt, UUID idSchedule, UUID idService, EStatusReceipt status, EStatusSchedule statusSchedule) {
        //Que cambie el tipo de servicio.
        if (!Objects.equals(receipt.getService().getId(), idService)) {
            ServiceDto _service = this.serviceServiceImpl.findById(idService);

            //Defino la nueva terapia
            receipt.setService(_service);
            //this.changeState(receipt, status);
        }
        //Que cambie el schedule
        if (!(Objects.equals(receipt.getSchedule().getId(), idSchedule))) {
            ScheduleDto _schedule = this.scheduleServiceImpl.findById(idSchedule);

            //Mando a cancelar el RECEIPT para que se libere el schedule.
            this.changeState(receipt, EStatusReceipt.CANCEL);
            //Actualizo el estado del receipt a CONFIRMED
            receipt.setStatus(status);
            //Defino el nuevo schedule
            _schedule.setStatus(statusSchedule);
            receipt.setSchedule(_schedule);
        }
        this.receiptRepositoryCommand.save(new Receipt(receipt));
    }

    @Override
    public void update(ReceiptDto receipt, UUID idSchedule, UUID idService, EStatusReceipt status, Double price, boolean express, String reasons) {
        /**
         * El cliente no puede cambiar el status a CONFIRMED, por lo cual, en la
         * peticion se debe de enviar status PRE-RESERVE o CANCEL.
         */
        receipt.setExpress(express);
        receipt.setPrice(price);
        receipt.setReasons(reasons);
        switch (status) {
            case CANCEL: {
                this.changeState(receipt, status);
            }
            case PRE_RESERVE: {
                if (receipt.getStatus().equals(EStatusReceipt.PRE_RESERVE)) {
                    this.changeServiceScheduleStatus(receipt, idSchedule, idService, status, EStatusSchedule.PRE_RESERVE);
                }
            }
            case CONFIRMED: {
                //Que se mantengan las condiciones de la pre-reserva
                if ((Objects.equals(receipt.getSchedule().getId(), idSchedule))
                        && (Objects.equals(receipt.getService().getId(), idService))) {
                    this.changeState(receipt, status);
                }
                this.changeServiceScheduleStatus(receipt, idSchedule, idService, status, EStatusSchedule.RESERVED);
            }
            default: {
                throw new BusinessException(DomainErrorMessage.STATUS_NOT_ACCEPTED, "Status not accepted.");
            }
        }
    }

    public void changeState(ReceiptDto receipt, EStatusReceipt status) {

        switch (status) {
            case CANCEL: {
                if (!receipt.getStatus().equals(EStatusReceipt.ATTENDED)) {
                    //Liberando el schedule
                    ScheduleDto _schedule = receipt.getSchedule();
                    _schedule.setStatus(EStatusSchedule.ACTIVE);
                    this.scheduleServiceImpl.create(_schedule);

                    receipt.setSchedule(null);

                    receipt.setStatus(status);
                    receiptRepositoryCommand.save(new Receipt(receipt));
                    //return new UpdateReceiptResponse("Appointment updated successfully.", true);
                } else {
                    throw new BusinessException(DomainErrorMessage.STATUS_NOT_ACCEPTED, "Status not accepted, the appointment was attended.");
                }
            }
            case CONFIRMED: {
                if (receipt.getStatus().equals(EStatusReceipt.PRE_RESERVE) || receipt.getStatus().equals(EStatusReceipt.CONFIRMED)) {
                    ScheduleDto _schedule = receipt.getSchedule();
                    _schedule.setStatus(EStatusSchedule.RESERVED);
                    receipt.setSchedule(_schedule);

                    receipt.setStatus(status);
                    receiptRepositoryCommand.save(new Receipt(receipt));
                    //return new UpdateReceiptResponse("Appointment updated successfully.", true);
                } else {
                    throw new BusinessException(DomainErrorMessage.STATUS_NOT_ACCEPTED, "Status not accepted, the appointment was attended.");
                    //return new UpdateReceiptResponse("Status not accepted, appointment status: " + receipt.getStatus().name(), false);
                }
            }
            case ATTENDED: {
                ScheduleDto _schedule = receipt.getSchedule();
                _schedule.setStatus(EStatusSchedule.ATTENDED);
                receipt.setSchedule(_schedule);

                receipt.setStatus(status);
                receiptRepositoryCommand.save(new Receipt(receipt));
            }
            default: {
                throw new BusinessException(DomainErrorMessage.STATUS_NOT_ACCEPTED, "Status not accepted.");
                //return new UpdateReceiptResponse("Status not accepted.", false);
            }
        }
    }

}
