package com.kynsoft.notification.application.command.advertisingcontent.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.util.CustomMultipartFile;
import com.kynsof.share.utils.ConfigureTimeZone;
import com.kynsoft.notification.domain.dto.AdvertisingContentDto;
import com.kynsoft.notification.domain.service.IAdvertisingContentService;
import com.kynsoft.notification.infrastructure.service.AmazonClient;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CreateAdvertisingContentCommandHandler implements ICommandHandler<CreateAdvertisingContentCommand> {

    private final IAdvertisingContentService service;
    private final AmazonClient amazonClient;

    public CreateAdvertisingContentCommandHandler(IAdvertisingContentService service, AmazonClient amazonClient) {
        this.service = service;
        this.amazonClient = amazonClient;
    }

    @Override
    public void handle(CreateAdvertisingContentCommand command) {
        String url = null;
        if (command.getImage() != null) {
            try {
                MultipartFile file = new CustomMultipartFile(command.getImage(), UUID.randomUUID().toString());
                url = amazonClient.save(file, file.getName());
            } catch (IOException ex) {
                Logger.getLogger(CreateAdvertisingContentCommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.service.create(new AdvertisingContentDto(
                command.getId(), 
                command.getTitle(), 
                command.getDescription(), 
                command.getType(),
                ConfigureTimeZone.getTimeZone(),
                null,
                url,
                command.getLink()
        ));
    }
}
