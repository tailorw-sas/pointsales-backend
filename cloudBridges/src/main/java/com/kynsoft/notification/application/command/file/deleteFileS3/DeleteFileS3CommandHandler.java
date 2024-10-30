package com.kynsoft.notification.application.command.file.deleteFileS3;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.AFileDto;
import com.kynsoft.notification.domain.service.IAFileService;
import com.kynsoft.notification.domain.service.IAmazonClient;
import org.springframework.stereotype.Component;

@Component
public class DeleteFileS3CommandHandler implements ICommandHandler<DeleteFileS3Command> {

    private final IAmazonClient amazonClient;
    private final IAFileService fileService;


    public DeleteFileS3CommandHandler(IAmazonClient amazonClient, IAFileService fileService) {

        this.amazonClient = amazonClient;
        this.fileService = fileService;
    }

    @Override
    public void handle(DeleteFileS3Command command) {

        AFileDto fileDto = fileService.findByUrl(command.getUrl());
        if (fileDto != null) {
            amazonClient.delete(command.getUrl());
            fileService.delete(fileDto);
            command.setResult(true);
        }

    }
}
