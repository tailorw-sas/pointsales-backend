package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.examOrder.create.CreateExamOrderCommand;
import com.kynsof.treatments.application.command.examOrder.create.CreateExamOrderMessage;
import com.kynsof.treatments.application.command.examOrder.create.CreateExamOrderRequest;
import com.kynsof.treatments.application.command.examOrder.delete.ExamOrderDeleteCommand;
import com.kynsof.treatments.application.command.examOrder.delete.ExamOrderDeleteMessage;
import com.kynsof.treatments.application.command.examOrder.update.UpdateExamOrderCommand;
import com.kynsof.treatments.application.command.examOrder.update.UpdateExamOrderMessage;
import com.kynsof.treatments.application.command.examOrder.update.UpdateExamOrderRequest;
import com.kynsof.treatments.application.query.examOrder.getById.FindByIdExamOrderQuery;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.application.query.examOrder.getall.GetAllExamOrderQuery;
import com.kynsof.treatments.application.query.examOrder.getexanorderbyidexternalconsultation.FindExamOrderByIdExternalConsultationQuery;
import com.kynsof.treatments.application.query.examOrder.search.GetSearchExamOrderQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/exam-order")
public class ExamOrderController {

    private final IMediator mediator;

    public ExamOrderController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateExamOrderMessage> create(@RequestBody CreateExamOrderRequest request) {
        CreateExamOrderCommand createCommand = CreateExamOrderCommand.fromRequest(request);
        CreateExamOrderMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") UUID patientId) {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllExamOrderQuery query = new GetAllExamOrderQuery(pageable, patientId);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExamOrderResponse> getById(@PathVariable UUID id) {

        FindByIdExamOrderQuery query = new FindByIdExamOrderQuery(id);
        ExamOrderResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchExamOrderQuery query = new GetSearchExamOrderQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/by-id-external-consultation/{id}")
    public ResponseEntity<ExamOrderResponse> findDiagnosisByIdExternalConsultation(@PathVariable UUID id) {

        FindExamOrderByIdExternalConsultationQuery query = new FindExamOrderByIdExternalConsultationQuery(id);
        ExamOrderResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UpdateExamOrderMessage> update(@PathVariable UUID id, @RequestBody UpdateExamOrderRequest request) {
        UpdateExamOrderCommand command = UpdateExamOrderCommand.fromRequest(request, id);
        UpdateExamOrderMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        ExamOrderDeleteCommand query = new ExamOrderDeleteCommand(id);
        ExamOrderDeleteMessage response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

}
