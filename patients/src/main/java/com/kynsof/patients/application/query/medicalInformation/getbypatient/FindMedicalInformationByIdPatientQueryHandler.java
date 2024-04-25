package com.kynsof.patients.application.query.medicalInformation.getbypatient;

import com.kynsof.patients.application.query.medicalInformation.getall.MedicalInformationResponse;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindMedicalInformationByIdPatientQueryHandler implements IQueryHandler<FindMedicalInformationByIdPatientQuery, MedicalInformationResponse>  {

    private final IMedicalInformationService serviceImpl;
    private final IPatientsService patientsService;

    public FindMedicalInformationByIdPatientQueryHandler(IMedicalInformationService serviceImpl, IPatientsService patientsService) {
        this.serviceImpl = serviceImpl;
        this.patientsService = patientsService;
    }

    @Override
    public MedicalInformationResponse handle(FindMedicalInformationByIdPatientQuery query) {
        PatientDto patient = this.patientsService.findByIdSimple(query.getId());
        MedicalInformationDto medicalInformationDto = serviceImpl.findByPatient(patient);

        return new MedicalInformationResponse(medicalInformationDto);
    }
}
