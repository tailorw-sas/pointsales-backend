package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.module.create.CreateModuleCommand;
import com.kynsof.identity.application.command.module.create.CreateModuleMessage;
import com.kynsof.identity.application.command.module.create.CreateModuleRequest;
import com.kynsof.identity.application.command.module.update.UpdateModuleCommand;
import com.kynsof.identity.application.command.module.update.UpdateModuleMessage;
import com.kynsof.identity.application.query.module.getbyid.FindModuleByIdQuery;
import com.kynsof.identity.application.query.module.getbyid.ModuleResponse;
import com.kynsof.identity.application.query.module.search.GetSearchModuleQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

    private final IMediator mediator;

    public ModuleController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<CreateModuleMessage> create(@RequestBody CreateModuleRequest request)  {
        CreateModuleCommand createCommand = CreateModuleCommand.fromRequest(request);
        CreateModuleMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModuleResponse> getById(@PathVariable UUID id) {

        FindModuleByIdQuery query = new FindModuleByIdQuery(id);
        ModuleResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchModuleQuery query = new GetSearchModuleQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CreateModuleRequest request) {

        UpdateModuleCommand command = UpdateModuleCommand.fromRequest(request, id);
        UpdateModuleMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
