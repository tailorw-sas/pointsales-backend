package com.kynsof.scheduled.application.query.receipt.getAll;

import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.share.core.domain.bus.query.IQuery;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class FindReceiptWithFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID resource;
    private UUID user;
    private UUID service;
    private UUID schedule;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    private EStatusReceipt status;
    private String filter;

}
