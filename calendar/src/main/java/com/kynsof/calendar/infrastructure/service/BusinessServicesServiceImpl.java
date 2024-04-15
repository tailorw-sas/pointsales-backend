package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.calendar.infrastructure.entity.BusinessServices;
import com.kynsof.calendar.infrastructure.repository.command.BusinessServicesWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BusinessServicesReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessServicesServiceImpl implements IBusinessServicesService {

    @Autowired
    private BusinessServicesWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessServicesReadDataJPARepository repositoryQuery;

    @Override
    public void create(BusinessServicesDto object) {
        this.repositoryCommand.save(new BusinessServices(object));
    }

    @Override
    public void update(BusinessServicesDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BusinessServicesDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
