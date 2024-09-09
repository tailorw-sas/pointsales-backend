package com.kynsoft.notification.domain.event.handler;

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
import java.util.stream.IntStream;

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
        Pageable pageable = PageRequest.of(0, 500, Sort.by(Sort.Direction.ASC, "emailListId"));

        int requestCount = 0;
        long startTime = System.currentTimeMillis();

        Page<EmailListDto> data;
        do {
            // Obtener el lote actual de destinatarios paginados
            data = emailListService.getEmailListByCampaignId(campaignId, pageable);

            // Procesar los destinatarios si hay contenido
            if (data.hasContent()) {
                List<List<EmailListDto>> batches = partitionList(data.getContent(), 50);

                for (List<EmailListDto> batch : batches) {
                    List<MailJetRecipient> mailJetRecipientList = convertToRecipients(batch);

                    SendMailJetEMailCommand command = createMailJetCommand(mailJetRecipientList, campaignDto);

                    // Manejar el envío y control de tasa
                    sendWithRateControl(command, requestCount, startTime);
                    requestCount++;
                }
            }

            // Avanzar a la siguiente página
            pageable = pageable.next();
        } while (data.hasNext());
    }

    private List<List<EmailListDto>> partitionList(List<EmailListDto> list, int size) {
        return IntStream.range(0, (list.size() + size - 1) / size)
                .mapToObj(i -> list.subList(i * size, Math.min(i * size + size, list.size())))
                .toList();
    }

    private List<MailJetRecipient> convertToRecipients(List<EmailListDto> batch) {
        return batch.stream()
                .map(emailListDto -> new MailJetRecipient(emailListDto.getEmail(), emailListDto.getClientName()))
                .toList();
    }

    private SendMailJetEMailCommand createMailJetCommand(List<MailJetRecipient> recipients, CampaignDto campaignDto) {
        return new SendMailJetEMailCommand(
                recipients,
                null,
                null,
                campaignDto.getSubject(),
                campaignDto.getTemplate().getTemplateCode()
        );
    }

    private void sendWithRateControl(SendMailJetEMailCommand command, int requestCount, long startTime) {
        mediator.send(command);

        // Controlar las llamadas para no exceder las 500 en 10 segundos
        if (requestCount >= 500) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < 10_000) {
                try {
                    Thread.sleep(10_000 - elapsedTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Reiniciar el contador y el tiempo de inicio
            requestCount = 0;
            startTime = System.currentTimeMillis();
        }
    }
//    public void onSendEmailEvent(SendEmailEvent event) {
//        String campaignId = event.getCamapaignId();
//        CampaignDto campaignDto = campaignService.getCampaignById(campaignId);
//        Pageable pageable = PageRequest.of(0, 500);
//        Page<EmailListDto> data;
//        do {
//            data = emailListService.getEmailListByCampaignId(campaignId, pageable);
//            List<MailJetRecipient> mailJetRecipientList = data.stream().map(emailListDto -> {
//                return new MailJetRecipient(emailListDto.getEmail(),emailListDto.getClientName());
//            }).toList();
//            SendMailJetEMailCommand command = new SendMailJetEMailCommand(mailJetRecipientList,
//                    null,
//                    null,
//                    campaignDto.getSubject(),
//                    campaignDto.getTemplate().getTemplateCode()
//                    );
//            mediator.send(command);
//            pageable = pageable.next();
//        } while (data.hasNext());
//
//    }
}
