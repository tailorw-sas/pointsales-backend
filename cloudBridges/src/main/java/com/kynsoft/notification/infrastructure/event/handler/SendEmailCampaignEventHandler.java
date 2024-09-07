package com.kynsoft.notification.infrastructure.event.handler;

import com.kynsoft.notification.domain.event.SendEmailEvent;
import org.springframework.stereotype.Component;

@Component
public class SendEmailCampaignEventHandler {


    public void onSendEmailEvent(SendEmailEvent event){
        //TODO la logica de enviar
    }
}
