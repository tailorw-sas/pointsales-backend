package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsoft.notification.application.TenantResponse;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.service.ITenantService;
import com.kynsoft.notification.infrastructure.entity.Tenant;
import com.kynsoft.notification.infrastructure.repository.command.TenantWriteDataJPARepository;
import com.kynsoft.notification.infrastructure.repository.query.TenantReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantServiceImpl implements ITenantService {

    @Autowired
    private TenantWriteDataJPARepository commandRepository;

    @Autowired
    private TenantReadDataJPARepository queryRepository;

    @Override
    public void create(TenantDto object) {
        this.commandRepository.save(new Tenant(object));
    }

    @Override
    public void update(TenantDto object) {
        Tenant update = new Tenant(object);
        this.commandRepository.save(update);
    }

    @Override
    public void delete(UUID id) {
        try {
            this.commandRepository.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public TenantDto findById(UUID id) {
        Optional<Tenant> userSystem = this.queryRepository.findById(id);
        if (userSystem.isPresent()) {
            return userSystem.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.OBJECT_NOT_FOUNT, new ErrorField("id", DomainErrorMessage.OBJECT_NOT_FOUNT.getReasonPhrase())));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {

        GenericSpecificationsBuilder<Tenant> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Tenant> data = this.queryRepository.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Tenant> data) {
        List<TenantResponse> responses = new ArrayList<>();
        for (Tenant p : data.getContent()) {
            responses.add(new TenantResponse(p.toAggregate()));
        }
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public TenantDto findByTenantId(String tenantId) {
        Optional<Tenant> userSystem = this.queryRepository.findByTenantId(tenantId);
        if (userSystem.isPresent()) {
            return userSystem.get().toAggregate();
        }
        return null;
    }

}
