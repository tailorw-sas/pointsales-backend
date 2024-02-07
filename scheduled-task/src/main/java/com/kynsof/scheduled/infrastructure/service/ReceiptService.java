package com.kynsof.scheduled.infrastructure.service;

import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.domain.dto.ReceiptDto;
import com.kynsof.scheduled.domain.dto.ScheduleDto;
import com.kynsof.scheduled.domain.service.IReceiptService;
import com.kynsof.scheduled.infrastructure.command.ReceiptWriteDataJPARepository;
import com.kynsof.scheduled.infrastructure.entity.Receipt;
import com.kynsof.scheduled.infrastructure.query.ReceiptReadDataJPARepository;
import com.kynsof.scheduled.infrastructure.query.ScheduleReadDataJPARepository;
import com.kynsof.scheduled.infrastructure.query.ServiceReadDataJPARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService implements IReceiptService {

    @Autowired
    private ReceiptReadDataJPARepository receiptRepositoryQuery;

    @Autowired
    private ReceiptWriteDataJPARepository receiptRepositoryCommand;

    @Autowired
    private ScheduleReadDataJPARepository scheduleRepositoryQuery;

    @Autowired
    private ServiceReadDataJPARepository serviceRepositoryQuery;

    @Override
    public void create(ReceiptDto receipt) {

        ScheduleDto _schedule = receipt.getSchedule();
        _schedule.setStatus(EStatusSchedule.PRE_RESERVE);
        receipt.setSchedule(_schedule);

        receipt.setStatus(EStatusReceipt.PRE_RESERVE);
        this.receiptRepositoryCommand.save(new Receipt(receipt));

    }

}