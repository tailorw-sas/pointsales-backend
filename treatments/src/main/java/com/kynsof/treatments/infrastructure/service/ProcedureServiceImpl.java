package com.kynsof.treatments.infrastructure.service;


import com.kynsof.treatments.application.query.procedure.getAll.ProcedureResponse;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.treatments.domain.service.IProcedureService;
import com.kynsof.treatments.infrastructure.entity.Procedure;
import com.kynsof.treatments.infrastructure.entity.specifications.ProcedureSpecifications;
import com.kynsof.treatments.infrastructure.repositories.query.ProcedureReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcedureServiceImpl implements IProcedureService {


    @Autowired
    private ProcedureReadDataJPARepository repositoryQuery;


    @Override
    public ProcedureDto findByCode(String code) {
        Optional<Procedure> procedureByCode = Optional.ofNullable(this.repositoryQuery.findProcedureByCode(code));
        if (procedureByCode.isPresent()) {
            return procedureByCode.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.PARAMETIRAZATION_NOT_FOUND, "Cie10 not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, String name, String code, String type) {
        ProcedureSpecifications specifications = new ProcedureSpecifications(code, name, type);
        Page<Procedure> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ProcedureResponse> procedureResponses = new ArrayList<>();
        for (Procedure p : data.getContent()) {
            procedureResponses.add(new ProcedureResponse(p.toAggregate()));
        }
        return new PaginatedResponse(procedureResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
