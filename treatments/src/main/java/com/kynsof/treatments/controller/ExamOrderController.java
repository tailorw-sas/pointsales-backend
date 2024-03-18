package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.examOrder.create.CreateExamOrderCommand;
import com.kynsof.treatments.application.command.examOrder.create.CreateExamOrderMessage;
import com.kynsof.treatments.application.command.examOrder.create.CreateExamOrderRequest;
import com.kynsof.treatments.application.query.examOrder.getById.FindByIdExamOrderQuery;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.application.query.examOrder.getall.GetAllExamOrderQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/exam-order")
public class ExamOrderController {

    private final IMediator mediator;

    public ExamOrderController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateExamOrderMessage> create(@RequestBody CreateExamOrderRequest request)  {
        CreateExamOrderCommand createCommand = CreateExamOrderCommand.fromRequest(request);
        CreateExamOrderMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID patientId)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllExamOrderQuery query = new GetAllExamOrderQuery(pageable,patientId);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExamOrderResponse> getById(@PathVariable UUID id) {

        FindByIdExamOrderQuery query = new FindByIdExamOrderQuery(id);
        ExamOrderResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

//    @PutMapping(path = "/{id}")
//    public ResponseEntity<UpdateExternalConsultationMessage> update(@PathVariable UUID id, @RequestBody UpdateExternalConsultationRequest request) {
//        UpdateExternalConsultationCommand command = UpdateExternalConsultationCommand.fromRequest(id,request );
//        UpdateExternalConsultationMessage response = mediator.send(command);
//        return ResponseEntity.ok(response);
//    }

}