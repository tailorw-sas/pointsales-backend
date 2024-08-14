package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.block.create.CreateBlockCommand;
import com.kynsof.calendar.application.command.block.create.CreateBlockMessage;
import com.kynsof.calendar.application.command.block.create.CreateBlockRequest;
import com.kynsof.calendar.application.command.block.delete.DeleteBlockCommand;
import com.kynsof.calendar.application.command.block.delete.DeleteBlockMessage;
import com.kynsof.calendar.application.command.block.update.UpdateBlockCommand;
import com.kynsof.calendar.application.command.block.update.UpdateBlockMessage;
import com.kynsof.calendar.application.command.block.update.UpdateBlockRequest;
import com.kynsof.calendar.application.query.BlockResponse;
import com.kynsof.calendar.application.query.block.getbyid.FindBlockByIdQuery;
import com.kynsof.calendar.application.query.block.search.GetSearchBlockQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/block")
public class BlockController {

    private final IMediator mediator;

    public BlockController(IMediator mediator){

        this.mediator = mediator;
    }
    @PostMapping("")
    public ResponseEntity<CreateBlockMessage> create(@RequestBody @Valid CreateBlockRequest request)  {
        CreateBlockCommand createCommand = CreateBlockCommand.fromRequest(request);
        CreateBlockMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchBlockQuery query = new GetSearchBlockQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BlockResponse> getById(@PathVariable UUID id) {

        FindBlockByIdQuery query = new FindBlockByIdQuery(id);
        BlockResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteBlockMessage> deleteServices(@PathVariable("id") UUID id) {

        DeleteBlockCommand command = new DeleteBlockCommand(id);
        DeleteBlockMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateBlockMessage> update(@PathVariable("id") UUID id,@RequestBody UpdateBlockRequest request) {

        UpdateBlockCommand command = UpdateBlockCommand.fromRequest(id,request);
        UpdateBlockMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
