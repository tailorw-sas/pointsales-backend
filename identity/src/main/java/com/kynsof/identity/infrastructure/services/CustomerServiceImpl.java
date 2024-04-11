package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.CustomerDto;
import com.kynsof.identity.domain.interfaces.service.ICustomerService;
import com.kynsof.identity.infrastructure.identity.Customer;
import com.kynsof.identity.infrastructure.repository.command.CustomerWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.CustomerReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerWriteDataJPARepository repositoryCommand;

    @Autowired
    private CustomerReadDataJPARepository repositoryQuery;

    @Override
    public void create(CustomerDto customer) {
        this.repositoryCommand.save(new Customer(customer));
    }

    @Override
    public void update(CustomerDto customer) {
        this.repositoryCommand.save(new Customer(customer));
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CustomerDto findById(UUID id) {
        
        Optional<Customer> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.CUSTOMER_NOT_FOUND, new ErrorField("id", "Customer not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
