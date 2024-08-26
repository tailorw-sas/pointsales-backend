package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ReceiptResponse;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusReceipt;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.calendar.infrastructure.entity.Receipt;
import com.kynsof.calendar.infrastructure.repository.command.ReceiptWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ReceiptReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public UUID create(ReceiptDto receipt) {

        if (receipt.getSchedule().getStatus() != EStatusSchedule.AVAILABLE) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_IS_NOT_AVAIBLE, "The selected schedule is not available.");
        }
        Receipt entity = this.receiptRepositoryCommand.save(new Receipt(receipt));
        return entity.getId();

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
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.RESOURCE_NOT_FOUND, new ErrorField("id", "Resource not found.")));
    }

    @Override
    public void update(ReceiptDto dto) {
        Receipt receipt = new Receipt(dto);
        this.receiptRepositoryCommand.save(receipt);
    }

    public void changeState(ReceiptDto receipt, EStatusReceipt status) {

        switch (status) {
            case CANCEL: {
                //TODO revisar la logica para poner agotada el scheduled
                if (!receipt.getStatus().equals(EStatusReceipt.ATTENDED)) {
                    //Liberando el schedule
                    ScheduleDto _schedule = receipt.getSchedule();
                    _schedule.setStatus(EStatusSchedule.AVAILABLE);
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
                   // _schedule.setStatus(EStatusSchedule.RESERVED);
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
               // _schedule.setStatus(EStatusSchedule.ATTENDED);
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

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    EStatusReceipt enumValue = EStatusReceipt.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum Receipt: " + filter.getValue());
                }
            }
        }
        GenericSpecificationsBuilder<Receipt> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Receipt> data = this.receiptRepositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public Optional<Receipt> findByRequestId(String requestId) {
        return this.receiptRepositoryQuery.findByRequestId(requestId);
    }

    private PaginatedResponse getPaginatedResponse(Page<Receipt> data) {
        List<ReceiptResponse> patients = new ArrayList<>();
        for (Receipt o : data.getContent()) {
            patients.add(new ReceiptResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public ReceiptDto findReceiptByUserIdAndScheduleId(UUID user, UUID schedule) {
        Optional<Receipt> object = this.receiptRepositoryQuery.findReceiptByUserIdAndScheduleId(user, schedule);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        return null;
        //throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.RESOURCE_NOT_FOUND, new ErrorField("id", "Receipt not found.")));
    }

}
