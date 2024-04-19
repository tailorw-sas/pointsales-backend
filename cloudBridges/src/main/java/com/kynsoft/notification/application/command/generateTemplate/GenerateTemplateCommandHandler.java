package com.kynsoft.notification.application.command.generateTemplate;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.service.IAdvertisingContentService;
import com.kynsoft.notification.infrastructure.service.AmazonClient;
import org.springframework.stereotype.Component;

@Component
public class GenerateTemplateCommandHandler implements ICommandHandler<GenerateTemplateCommand> {



    public GenerateTemplateCommandHandler(IAdvertisingContentService service, AmazonClient amazonClient) {

    }

    @Override
    public void handle(GenerateTemplateCommand command) {
    }
}
