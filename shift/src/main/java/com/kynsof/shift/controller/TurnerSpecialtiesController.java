package com.kynsof.shift.controller;

import com.kynsof.calendar.application.command.tunerSpecialties.importExcel.ImportTunerSpecialtiesCommand;
import com.kynsof.calendar.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.calendar.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;
import com.kynsof.calendar.application.query.tunerSpecialties.importExcel.ImportTurnerSpecialtiesStatusQuery;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesMessage;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesRequest;
import com.kynsof.shift.application.command.tunerSpecialties.delete.DeleteTurnerSpecialtiesCommand;
import com.kynsof.shift.application.command.tunerSpecialties.delete.DeleteTurnerSpecialtiesMessage;
import com.kynsof.shift.application.command.tunerSpecialties.update.UpdateTurnerSpecialtiesCommand;
import com.kynsof.shift.application.command.tunerSpecialties.update.UpdateTurnerSpecialtiesMessage;
import com.kynsof.shift.application.command.tunerSpecialties.update.UpdateTurnerSpecialtiesRequest;
import com.kynsof.shift.application.query.TurnerSpecialtiesResponse;
import com.kynsof.shift.application.query.tunerSpecialties.getById.FindTurnerSpecialtiesByIdQuery;
import com.kynsof.shift.application.query.tunerSpecialties.search.GetSearchTurnerSpecialtiesQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.aspectj.bridge.IMessage;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/turner-specialties")
public class TurnerSpecialtiesController {

    private final IMediator mediator;

    public TurnerSpecialtiesController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateTurnerSpecialtiesRequest request) {

        CreateTurnerSpecialtiesCommand createCommand = CreateTurnerSpecialtiesCommand.fromRequest(request);
        CreateTurnerSpecialtiesMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteTurnerSpecialtiesMessage> delete(@PathVariable("id") UUID id) {

        DeleteTurnerSpecialtiesCommand command = new DeleteTurnerSpecialtiesCommand(id);
        DeleteTurnerSpecialtiesMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateTurnerSpecialtiesRequest request) {

        UpdateTurnerSpecialtiesCommand command = UpdateTurnerSpecialtiesCommand.fromRequest(request, id);
        UpdateTurnerSpecialtiesMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindTurnerSpecialtiesByIdQuery query = new FindTurnerSpecialtiesByIdQuery(id);
        TurnerSpecialtiesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchTurnerSpecialtiesQuery query = new GetSearchTurnerSpecialtiesQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<?>> importPayment(@RequestPart("file") FilePart filePart,
                                                 @RequestPart("importProcessId") String businessId){
        return DataBufferUtils.join(filePart.content())
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);

                    ImportTurnerSpecialtiesRequest importTurnerSpecialtiesRequest = new ImportTurnerSpecialtiesRequest(bytes,businessId);
                    ImportTunerSpecialtiesCommand importTunerSpecialtiesCommand = new ImportTunerSpecialtiesCommand(importTurnerSpecialtiesRequest);
                    try {
                        IMessage message = mediator.send(importTunerSpecialtiesCommand);
                        return Mono.just(ResponseEntity.ok(message));// Mono.just(ResponseEntity.ok(message));
                    }catch (Exception e) {
                        return Mono.error(e);
                    }

                } );
    }

    @GetMapping("/{importProcessId}/import-status")
    public ResponseEntity<ImportProcessStatusResponse> importTurnerSpecialties(@PathVariable("importProcessId") String importProcessId){
        ImportTurnerSpecialtiesStatusQuery statusQuery = new ImportTurnerSpecialtiesStatusQuery(importProcessId);
        return ResponseEntity.ok(mediator.send(statusQuery));
    }
//    @PostMapping("/import-search")
//    public ResponseEntity<?> importPayment(@RequestBody SearchRequest request){
//        ImportTurnerSpecialtiesStatusQuery query= new ImportTurnerSpecialtiesStatusQuery();
//        return ResponseEntity.ok(mediator.send(command));
//    }

}
