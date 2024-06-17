package com.kynsof.rrhh.application.query.doctor.getbyid;

import com.kynsof.rrhh.domain.dto.DoctorDto;
import com.kynsof.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdDoctorQueryHandler implements IQueryHandler<FindByIdDoctorQuery, DoctorResponse>  {

    private final IDoctorService serviceImpl;

    public FindByIdDoctorQueryHandler(IDoctorService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public DoctorResponse handle(FindByIdDoctorQuery query) {
        DoctorDto doctorDto = serviceImpl.findById(query.getId());

        return new DoctorResponse(doctorDto);
    }
}
