package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.PaymentDevDto;
import com.kynsof.identity.domain.interfaces.service.IPaymentDevService;
import com.kynsof.identity.infrastructure.identity.PaymentDev;
import com.kynsof.identity.infrastructure.repository.command.PaymentDevWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PaymentDevReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentDevServiceImpl implements IPaymentDevService {

    @Autowired
    private PaymentDevWriteDataJPARepository repositoryCommand;

    @Autowired
    private PaymentDevReadDataJPARepository repositoryQuery;

    @Override
    public void create(PaymentDevDto paymentDevDto) {
        this.repositoryCommand.save(new PaymentDev(paymentDevDto));
    }

    @Override
    public void update(PaymentDevDto paymentDevDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PaymentDevDto paymentDevDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaymentDevDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
