package com.kynsof.store.infrastructure.services;


import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.store.application.query.customer.getAll.CustomerResponse;
import com.kynsof.store.domain.dto.CustomerDto;
import com.kynsof.store.domain.services.ICustomerService;
import com.kynsof.store.infrastructure.entity.Customer;
import com.kynsof.store.infrastructure.repositories.command.CustomerWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.CustomerReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UUID create(CustomerDto categoryDto) {
        Customer additionalInformation = this.repositoryCommand.save(new Customer(categoryDto));
        return additionalInformation.getId();
    }

    @Override
    public UUID update(CustomerDto customerDto) {

        if (customerDto == null || customerDto.getId() == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        return repositoryQuery.findById(customerDto.getId())
                .map(customer -> {
                    customer.setFirstName(customerDto.getFirstName() != null ? customerDto.getFirstName() : customer.getFirstName());
                    customer.setLastName(customerDto.getLastName() != null ? customerDto.getLastName() : customer.getLastName());
                    customer.setEmail(customerDto.getEmail() != null ? customerDto.getEmail() : customer.getEmail());
                    customer.setPhone(customerDto.getPhone() != null ? customerDto.getPhone() : customer.getPhone());
                    return repositoryCommand.save(customer);
                })
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerDto.getId() + " not found"))
                .getId();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public CustomerDto findById(UUID id) {
        Optional<Customer> category = this.repositoryQuery.findById(id);
        if (category.isPresent()) {
            return category.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Customer not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<Customer> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {

        GenericSpecificationsBuilder<Customer> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Customer> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Customer> data) {
        List<CustomerResponse> patients = new ArrayList<>();
        for (Customer p : data.getContent()) {
            patients.add(new CustomerResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
