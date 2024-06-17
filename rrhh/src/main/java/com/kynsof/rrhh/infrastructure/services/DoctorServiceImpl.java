package com.kynsof.rrhh.infrastructure.services;

import com.kynsof.rrhh.application.query.doctor.getbyid.DoctorResponse;
import com.kynsof.rrhh.application.query.users.getbyid.UserSystemsByIdResponse;
import com.kynsof.rrhh.domain.dto.DoctorDto;
import com.kynsof.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsof.rrhh.infrastructure.identity.Doctor;
import com.kynsof.rrhh.infrastructure.identity.UserSystem;
import com.kynsof.rrhh.infrastructure.repository.command.DoctorWriteDataJPARepository;
import com.kynsof.rrhh.infrastructure.repository.query.DoctorReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private final DoctorWriteDataJPARepository repositoryCommand;

    private final DoctorReadDataJPARepository repositoryQuery;

    public DoctorServiceImpl(DoctorWriteDataJPARepository repositoryCommand, DoctorReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public void create(DoctorDto object) {
        this.repositoryCommand.save(new Doctor(object));
    }

    @Override
    public void update(DoctorDto object) {
        this.repositoryCommand.save(new Doctor(object));
    }

    @Override
    public void delete(DoctorDto object) {
        this.repositoryCommand.save(new Doctor(object));
    }

    @Override
    public DoctorDto findById(UUID id) {
        
        Optional<Doctor> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.DEVICE_NOT_FOUND, new ErrorField("Doctor.id", "Doctor not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Doctor> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Doctor> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Doctor> data) {
        List<DoctorResponse> patients = new ArrayList<>();
        for (Doctor o : data.getContent()) {
            patients.add(new DoctorResponse(o.toAggregate()));
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


}
