package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.application.query.getall.PaginatedResponse;
import com.kynsof.patients.application.query.getall.PatientsResponse;
import com.kynsof.patients.domain.EStatusPatients;
import com.kynsof.patients.domain.PatientDto;
import com.kynsof.patients.domain.exception.BusinessException;
import com.kynsof.patients.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.command.PatientsWriteDataJPARepository;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.patients.infrastructure.entity.specifications.PatientsSpecifications;
import com.kynsof.patients.infrastructure.query.PatientsReadDataJPARepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientsServiceImpl implements IPatientsService {

    @Autowired
    private PatientsWriteDataJPARepository repositoryCommand;

    @Autowired
    private PatientsReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(PatientDto patients) {
        this.repositoryCommand.save(new Patients(patients));
        return patients.getId();
    }

    @Override
    public UUID update(PatientDto patient) {
        Optional<Patients> updatePatient = this.repositoryQuery.findById(patient.getId());
        Patients patients = updatePatient.get();
        if (patient.getName() != null) {
            patients.setName(patient.getName());
        }

        if (patient.getLastName() != null) {
            patients.setLastName(patient.getLastName());
        }

        if (patient.getIdentification() != null) {
            patients.setIdentification(patient.getIdentification());
        }

        if (patient.getGender() != null) {
            patients.setIdentification(patient.getGender());
        }

        if (patient.getStatus() != null) {
            patients.setStatus(patient.getStatus());
        }

        this.repositoryCommand.save(patients);
        return patient.getId();
    }

    @Override
    public PatientDto findById(UUID id) {
        Optional<Patients> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        //throw new RuntimeException("Patients not found.");
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Patients not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idPatients, String identification) {
        PatientsSpecifications specifications = new PatientsSpecifications(idPatients, identification);
        Page<Patients> data = this.repositoryQuery.findAll(specifications, pageable);

        List<PatientsResponse> patients = new ArrayList<>();
        for (Patients p : data.getContent()) {
            patients.add(new PatientsResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        PatientDto patientDelete = this.findById(id);
        patientDelete.setStatus(EStatusPatients.INACTIVE);
        
        this.repositoryCommand.save(new Patients(patientDelete));
    }

}
