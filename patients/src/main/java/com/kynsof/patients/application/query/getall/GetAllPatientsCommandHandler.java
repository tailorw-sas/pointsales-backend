package com.kynsof.patients.application.query.getall;

import com.kynsof.patients.infrastructure.service.PatientsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllPatientsCommandHandler {

    @Autowired
    private PatientsServiceImpl serviceImpl;
    
    public PaginatedResponse handle(FindPatientsWithFilterQuery query) {

        return this.serviceImpl.findAll(query.getPageable(), query.getIdPatients(), query.getIdentification());
    }
}
