package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.domain.dto.AdvertisingContentDto;
import com.kynsoft.notification.domain.service.IAdvertisingContentService;
import com.kynsoft.notification.infrastructure.entity.AdvertisingContent;
import com.kynsoft.notification.infrastructure.repository.command.AdvertisingContentWriteDataJPARepository;
import com.kynsoft.notification.infrastructure.repository.query.AdvertisingContentReadDataJPARepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

@Service
public class AdvertisingContentServiceImpl implements IAdvertisingContentService {

    @Autowired
    private AdvertisingContentWriteDataJPARepository commandRepository;

    @Autowired
    private AdvertisingContentReadDataJPARepository queryRepository;

    @Override
    public void create(AdvertisingContentDto object) {
        this.commandRepository.save(new AdvertisingContent(object));
    }

    @Override
    public void update(AdvertisingContentDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AdvertisingContentDto findById(UUID id) {
        
        Optional<AdvertisingContent> object = this.queryRepository.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, new ErrorField("id", "AdvertisingContent not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
