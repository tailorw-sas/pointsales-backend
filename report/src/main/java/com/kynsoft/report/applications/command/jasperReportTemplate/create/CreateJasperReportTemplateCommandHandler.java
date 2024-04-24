package com.kynsoft.report.applications.command.jasperReportTemplate.create;

import com.kynsoft.report.domain.dto.JasperReportTemplateDto;
import com.kynsoft.report.domain.services.IAmazonClient;
import com.kynsoft.report.domain.services.IJasperReportTemplateService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.util.CustomMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CreateJasperReportTemplateCommandHandler implements ICommandHandler<CreateJasperReportTemplateCommand> {

    private final IJasperReportTemplateService service;
    private final IAmazonClient amazonClient;

    public CreateJasperReportTemplateCommandHandler(IJasperReportTemplateService service, IAmazonClient amazonClient) {
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
