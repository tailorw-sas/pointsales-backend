package com.kynsof.patients.infrastructure.service;

import com.kynsof.patients.application.query.getall.PaginatedResponse;
import com.kynsof.patients.application.query.getall.PatientsResponse;
import com.kynsof.patients.domain.Patients;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.command.PatientsWriteDataJPARepository;
import com.kynsof.patients.infrastructure.dto.PatientsDAO;
import com.kynsof.patients.infrastructure.query.PatientsReadDataJPARepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientsServiceImpl implements IPatientsService {

    @Autowired
    private PatientsWriteDataJPARepository repositoryCommand;

    @Autowired
    private PatientsReadDataJPARepository repositoryQuery;

    @Override
    public void createService(Patients patients) {
        this.repositoryCommand.save(new PatientsDAO(
                patients.getId(), 
                patients.getIdentification(), 
                patients.getName(), 
                patients.getLastName(), 
                patients.getGender()
        ));
    }

    @Override
    public Patients findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<PatientsDAO> data = this.repositoryQuery.findAll(pageable);
        List<PatientsResponse> patients = new ArrayList<>();
        for (PatientsDAO p : data.getContent()) {
            patients.add(new PatientsResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
