package com.kynsof.treatments.controller;

import com.kynsof.treatments.application.query.cie10.getAll.Cie10Response;
import com.kynsof.treatments.application.query.cie10.getAll.GetAllCie10Query;
import com.kynsof.treatments.application.query.cie10.getByCode.FindByCodeCie10Query;
import com.kynsof.treatments.application.query.procedure.getAll.GetAllProcedureQuery;
import com.kynsof.treatments.application.query.procedure.getAll.ProcedureResponse;
import com.kynsof.treatments.application.query.procedure.getByCode.FindByCodeProcedureQuery;
import com.kynsof.treatments.domain.bus.IMediator;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procedure")
public class ProcedureController {

    private final IMediator mediator;

    public ProcedureController(IMediator mediator){

        this.mediator = mediator;
    }



    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String name,
                                                           @RequestParam(defaultValue = "") String code,
                                                    @RequestParam(defaultValue = "") String type )
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllProcedureQuery query = new GetAllProcedureQuery(pageable, name, code, type);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<ProcedureResponse> getById(@PathVariable String code) {

        FindByCodeProcedureQuery query = new FindByCodeProcedureQuery(code);
        ProcedureResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}