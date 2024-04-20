package com.kynsoft.notification.application.command.jasperreporttemplate.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.util.CustomMultipartFile;
import com.kynsoft.notification.domain.dto.JasperReportTemplateDto;
import com.kynsoft.notification.domain.service.IJasperReportTemplateService;
import com.kynsoft.notification.infrastructure.service.AmazonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CreateJasperReportTemplateCommandHandler implements ICommandHandler<CreateJasperReportTemplateCommand> {

    private final IJasperReportTemplateService service;
    private final AmazonClient amazonClient;

    public CreateJasperReportTemplateCommandHandler(IJasperReportTemplateService service, AmazonClient amazonClient) {
        this.service = service;
        this.amazonClient = amazonClient;
    }

    @Override
    public void handle(CreateJasperReportTemplateCommand command) {
        String url = null;
        if (command.getFile() != null) {
            try {
                MultipartFile file = new CustomMultipartFile(command.getFile(), command.getName() + " " + UUID.randomUUID().toString());
               String fileName = file.getOriginalFilename()+".jrxml";
                url = amazonClient.save(file,fileName);
            } catch (IOException ex) {
                Logger.getLogger(CreateJasperReportTemplateCommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.service.create(new JasperReportTemplateDto(
                command.getId(), 
                command.getCode(), 
                command.getName(), 
                command.getDescription(), 
                url,
                command.getType(),
                command.getParameters()
        ));
    }
}
