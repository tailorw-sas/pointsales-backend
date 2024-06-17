package com.kynsoft.rrhh.controller;


import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.rrhh.application.command.assistant.create.CreateAssistantCommand;
import com.kynsoft.rrhh.application.command.assistant.create.CreateAssistantMessage;
import com.kynsoft.rrhh.application.command.assistant.create.CreateAssistantRequest;
import com.kynsoft.rrhh.application.command.assistant.delete.DeleteAssistantCommand;
import com.kynsoft.rrhh.application.command.assistant.delete.DeleteAssistantMessage;
import com.kynsoft.rrhh.application.command.assistant.update.UpdateAssistantCommand;
import com.kynsoft.rrhh.application.command.assistant.update.UpdateAssistantMessage;
import com.kynsoft.rrhh.application.command.assistant.update.UpdateAssistantRequest;
import com.kynsoft.rrhh.application.query.assistant.getbyid.AssistantResponse;
import com.kynsoft.rrhh.application.query.assistant.getbyid.FindByIdAssistantQuery;
import com.kynsoft.rrhh.application.query.assistant.search.GetSearchAssistantQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    private final IMediator mediator;

    public AssistantController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateAssistantRequest request)  {
        CreateAssistantCommand createCommand = CreateAssistantCommand.fromRequest(request);
        CreateAssistantMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindByIdAssistantQuery query = new FindByIdAssistantQuery(id);
        AssistantResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchAssistantQuery query = new GetSearchAssistantQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {

        DeleteAssistantCommand command = new DeleteAssistantCommand(id);
        DeleteAssistantMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id,@RequestBody UpdateAssistantRequest request) {

        UpdateAssistantCommand command = UpdateAssistantCommand.fromRequest(request,id);
        UpdateAssistantMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
