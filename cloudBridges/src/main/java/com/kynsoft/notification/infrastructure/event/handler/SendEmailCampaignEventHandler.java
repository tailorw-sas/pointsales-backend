package com.kynsoft.notification.infrastructure.event.handler;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.sendMailjetEmail.SendMailJetEMailCommand;
import com.kynsoft.notification.domain.dto.CampaignDto;
import com.kynsoft.notification.domain.dto.EmailListDto;
import com.kynsoft.notification.domain.dto.MailJetRecipient;
import com.kynsoft.notification.domain.event.SendEmailEvent;
import com.kynsoft.notification.domain.service.CampaignService;
import com.kynsoft.notification.domain.service.EmailListService;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendEmailCampaignEventHandler {

    private final IMediator mediator;
    private final EmailListService emailListService;
    private final CampaignService campaignService;

    public SendEmailCampaignEventHandler(IMediator mediator,
                                         EmailListService emailListService,
                                         CampaignService campaignService) {
        this.mediator = mediator;
        this.emailListService = emailListService;
        this.campaignService = campaignService;
    }

    @EventListener
    public void onSendEmailEvent(SendEmailEvent event) {
        String campaignId = event.getCamapaignId();
        CampaignDto campaignDto = campaignService.getCampaignById(campaignId);
        Pageable pageable = PageRequest.of(0, 500, Sort.by(Sort.Direction.ASC, "email_list_id"));
        Page<EmailListDto> data;
        do {
            data = emailListService.getEmailListByCampaignId(campaignId, pageable);
            List<MailJetRecipient> mailJetRecipientList = data.stream().map(emailListDto -> {
                return new MailJetRecipient(emailListDto.getEmail(),emailListDto.getClientName());
            }).toList();
            SendMailJetEMailCommand command = new SendMailJetEMailCommand(mailJetRecipientList,
                    null,
                    null,
                    campaignDto.getSubject(),
                    campaignDto.getTemplate().getTemplateCode()
                    );
            mediator.send(command);
            pageable = pageable.next();
        } while (data.hasNext());

    }
}
