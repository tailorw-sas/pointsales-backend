package com.kynsoft.report.applications.command.jasperReportTemplate.update;

import com.kynsoft.report.applications.command.jasperReportTemplate.create.CreateJasperReportTemplateCommandHandler;
import com.kynsoft.report.domain.dto.JasperReportTemplateDto;
import com.kynsoft.report.domain.services.IAmazonClient;
import com.kynsoft.report.domain.services.IJasperReportTemplateService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.core.infrastructure.util.CustomMultipartFile;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UpdateJasperReportTemplateCommandHandler implements ICommandHandler<UpdateJasperReportTemplateCommand> {

    private final IJasperReportTemplateService service;
    private final IAmazonClient amazonClient;

    public UpdateJasperReportTemplateCommandHandler(IJasperReportTemplateService service, IAmazonClient amazonClient) {
        this.service = service;
        this.amazonClient = amazonClient;
    }

    @Override
    public void handle(UpdateJasperReportTemplateCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "JasperReportTemplate ID cannot be null."));
        JasperReportTemplateDto update = this.service.findById(command.getId());

        if (command.getFile() != null) {
            try {
                MultipartFile file = new CustomMultipartFile(command.getFile(), command.getName() + " " + UUID.randomUUID().toString());
                String url = amazonClient.save(file, file.getName());
                update.setTemplateContentUrl(url);
            } catch (IOException ex) {
                Logger.getLogger(CreateJasperReportTemplateCommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        UpdateIfNotNull.updateIfNotNull(update::setTemplateCode, command.getCode());
        UpdateIfNotNull.updateIfNotNull(update::setTemplateDescription, command.getDescription());
        UpdateIfNotNull.updateIfNotNull(update::setTemplateName, command.getName());
        UpdateIfNotNull.updateIfNotNull(update::setParameters, command.getParameters());
        update.setType(command.getType());

        this.service.update(update);
    }
}
