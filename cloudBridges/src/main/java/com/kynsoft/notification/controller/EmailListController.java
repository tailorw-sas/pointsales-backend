package com.kynsoft.notification.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.emaillist.importEmailList.ImportEmailListCommand;
import com.kynsoft.notification.application.command.emaillist.importEmailList.ImportEmailListRequest;
import com.kynsoft.notification.application.query.emaillist.GetEmailListByCampaignIdQuery;
import com.kynsoft.notification.application.query.emaillist.importError.ImportEmailListErrorQuery;
import com.kynsoft.notification.application.query.emaillist.importError.ImportEmailListErrorRequest;
import com.kynsoft.notification.application.query.emaillist.processStatus.ImportEmailListProcessStatusQuery;
import com.kynsoft.notification.application.query.emaillist.processStatus.ImportEmailListProcessStatusRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email-list")
public class EmailListController {

    private final IMediator mediator;

    public EmailListController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/import")
    public ResponseEntity<?> createEmailList(
            @RequestPart("file") FilePart emailListFile,
            @RequestPart("importProcessId") String importProcessId,
            @RequestPart("campaignId") String campaignId

    ){
        ImportEmailListRequest importEmailListRequest = new ImportEmailListRequest(emailListFile,importProcessId,campaignId);
        ImportEmailListCommand command = new ImportEmailListCommand(importEmailListRequest);
        return ResponseEntity.ok(mediator.send(command));
    }
    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getAllEmailListByCampaignId(@PathVariable("campaignId") String campaignId){
        GetEmailListByCampaignIdQuery query = new GetEmailListByCampaignIdQuery(campaignId);
        return ResponseEntity.ok(mediator.send(query));
    }

    @PostMapping(path = "/import-search")
    public ResponseEntity<?> getImportEmailListError(@RequestBody SearchRequest searchRequest) {
        ImportEmailListErrorRequest request = new ImportEmailListErrorRequest(searchRequest.getQuery(),
                PageRequest.of(searchRequest.getPage(), searchRequest.getPageSize()));
        ImportEmailListErrorQuery query = new ImportEmailListErrorQuery(request);
        return ResponseEntity.ok(mediator.send(query));
    }

    @GetMapping(path = "/{importProcessId}/import-status")
    public ResponseEntity<?> getImportEmailListProcessStatus(@PathVariable("importProcessId") String importProcessId) {
        ImportEmailListProcessStatusRequest request = new ImportEmailListProcessStatusRequest(importProcessId);
        ImportEmailListProcessStatusQuery query = new ImportEmailListProcessStatusQuery(request);
        return ResponseEntity.ok(mediator.send(query));
    }

}
