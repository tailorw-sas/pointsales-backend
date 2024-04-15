package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.calendar.infrastructure.entity.BusinessResource;
import com.kynsof.calendar.infrastructure.repository.command.BusinessResourceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BusinessResourceReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessResourceServiceImpl implements IBusinessResourceService {

    @Autowired
    private BusinessResourceWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessResourceReadDataJPARepository repositoryQuery;

    @Override
    public void create(BusinessResourceDto object) {
        this.repositoryCommand.save(new BusinessResource(object));
    }

    @Override
    public void update(BusinessResourceDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BusinessResourceDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
