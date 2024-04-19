package com.kynsoft.notification.application.command.generateTemplate;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsoft.notification.domain.dto.ContentType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenerateTemplateCommand implements ICommand {

    private UUID id;
    private String title;
    private String description;
    private ContentType type;
    private byte[] image;
    private String link;

    public GenerateTemplateCommand(String title, String description, ContentType type, byte[] image, String link) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.type = type;
        this.image = image;
        this.link = link;
    }

    public static GenerateTemplateCommand fromRequest(GenerateTemplateRequest request) {
        return new GenerateTemplateCommand(request.getTitle(), request.getDescription(), request.getType(), request.getImage(), request.getLink());
    }

    @Override
    public ICommandMessage getMessage() {
        return new GenerateTemplateMessage(id);
    }
}
