package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.session.search.SessionResponse;
import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import com.kynsof.identity.domain.interfaces.service.ISessionService;
import com.kynsof.identity.infrastructure.identity.Session;
import com.kynsof.identity.infrastructure.repository.command.SessionWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.SessionReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements ISessionService {

    private final SessionWriteDataJPARepository repositoryCommand;
    private final SessionReadDataJPARepository repositoryQuery;

    @Autowired
    public SessionServiceImpl(SessionWriteDataJPARepository repositoryCommand, SessionReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public void create(SessionDto object) {
        this.repositoryCommand.save(new Session(object));
    }

    @Override
    public void update(SessionDto objectDto) {
        Session update = new Session(objectDto);
        update.setUpdatedAt(LocalDateTime.now());
        this.repositoryCommand.save(update);
    }

    @Override
    public void delete(UUID id) {
        SessionDto objectDelete = this.findById(id);
        objectDelete.setStatus(ESessionStatus.INACTIVE);
        objectDelete.setDeleteAt(ConfigureTimeZone.getTimeZone());
        objectDelete.setDeleted(true);
        this.repositoryCommand.save(new Session(objectDelete));
    }

    @Override
    public SessionDto getById(UUID id) {
        Optional<Session> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException
                (DomainErrorMessage.SESSION_NOT_FOUND,
                new ErrorField("id", "Session not found.")));
    }

    @Override
    public SessionDto findById(UUID id) {
        Optional<Session> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        else {
            throw new BusinessNotFoundException(new GlobalBusinessException(
                    DomainErrorMessage.SESSION_NOT_FOUND,
                    new ErrorField("Session.id", "Session not found.")));
        }
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCriteria(filterCriteria);
        GenericSpecificationsBuilder<Session> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Session> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private void filterCriteria(List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    ESessionStatus enumValue = ESessionStatus.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid value for enum ESessionStatus: " + filter.getValue());
                }
            }
        }
    }

    private PaginatedResponse getPaginatedResponse(Page<Session> data) {
        List<SessionResponse> sessionResponses = new ArrayList<>();
        for (Session s : data.getContent()) {
            sessionResponses.add(new SessionResponse(s.toAggregate()));
        }
        return new PaginatedResponse(sessionResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public List<SessionDto> findAll() {
        return this.repositoryQuery.findAll().stream()
                .map(Session::toAggregate)
                .collect(Collectors.toList());
    }
}
