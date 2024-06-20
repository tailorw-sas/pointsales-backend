package com.kynsoft.rrhh.infrastructure.services;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsoft.rrhh.application.query.assistant.getbyid.AssistantResponse;
import com.kynsoft.rrhh.application.query.users.getbyid.UserSystemsByIdResponse;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import com.kynsoft.rrhh.infrastructure.identity.Assistant;
import com.kynsoft.rrhh.infrastructure.identity.UserSystem;
import com.kynsoft.rrhh.infrastructure.repository.command.AssistantWriteDataJPARepository;
import com.kynsoft.rrhh.infrastructure.repository.query.AssistantReadDataJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssistantServiceImpl implements IAssistantService {

    private final AssistantWriteDataJPARepository repositoryCommand;

    private final AssistantReadDataJPARepository repositoryQuery;

    public AssistantServiceImpl(AssistantWriteDataJPARepository repositoryCommand, AssistantReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public void create(AssistantDto object) {
        this.repositoryCommand.save(new Assistant(object));
    }

    @Override
    public void update(AssistantDto object) {
        this.repositoryCommand.save(new Assistant(object));
    }

    @Override
    public void delete(AssistantDto object) {
        this.repositoryCommand.save(new Assistant(object));
    }

    @Override
    public AssistantDto findById(UUID id) {

        Optional<Assistant> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.DEVICE_NOT_FOUND, new ErrorField("Assistant.id", "Assistant not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Assistant> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Assistant> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Assistant> data) {
        List<AssistantResponse> patients = new ArrayList<>();
        for (Assistant o : data.getContent()) {
            patients.add(new AssistantResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    private PaginatedResponse getPaginatedResponseUserSystem(Page<UserSystem> data) {
        List<UserSystemsByIdResponse> users = new ArrayList<>();
        for (UserSystem o : data.getContent()) {
            users.add(new UserSystemsByIdResponse(o.toAggregate()));
        }
        return new PaginatedResponse(users, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public Long countByIdentificationAndNotId(String identification) {
        return this.repositoryQuery.countByIdentificationAndNotId(identification);
    }

    @Override
    public Long countByEmailAndNotId(String email) {
        return this.repositoryQuery.countByEmailAndNotId(email);
    }

}
