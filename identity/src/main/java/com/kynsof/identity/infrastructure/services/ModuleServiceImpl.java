package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.infrastructure.identity.SystemModule;
import com.kynsof.identity.infrastructure.repository.command.ModuleWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.ModuleReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleWriteDataJPARepository commandRepository;

    @Autowired
    private ModuleReadDataJPARepository queryRepository;

    @Override
    public void create(ModuleDto object) {
        this.commandRepository.save(new SystemModule(object));
    }

    @Override
    public void update(ModuleDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModuleDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
