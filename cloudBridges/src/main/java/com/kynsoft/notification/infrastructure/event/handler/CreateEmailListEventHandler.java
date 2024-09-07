package com.kynsoft.notification.infrastructure.event.handler;

import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import com.kynsoft.notification.domain.dto.EmailListDto;
import com.kynsoft.notification.domain.event.CreateEmailListEvent;
import com.kynsoft.notification.domain.service.EmailListService;
import com.kynsoft.notification.domain.service.ImportEmailListService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class CreateEmailListEventHandler {
    private final EmailListService emailListService;

    public CreateEmailListEventHandler(EmailListService emailListService) {
        this.emailListService = emailListService;
    }


    @EventListener
    public void onCreateEmailListEvent(CreateEmailListEvent event){
       List<ImportEmailListRow> emailListRow = event.getImportEmailListRow();
     List<EmailListDto>  emailListDtos =  emailListRow.stream()
               .map(row-> EmailListDto.builder()
                       .email(row.getEmail())
                       .clientName(row.getClientName())
                       .clientLastname(row.getClientLastname())
                       .build()
       ).toList();
        emailListService.createBulkEmailList(emailListDtos);
    }
}
