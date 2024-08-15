package com.kynsoft.notification.application.command.mailjetConfiguration.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateMailjetConfigurationCommand implements ICommand {
    private UUID id;
    private final String mailjetApiKey;
    private final String mailjetApiSecret;
    private final String fromEmail;
    private final String fromName;
    private String tenant;

    public UpdateMailjetConfigurationCommand(UUID id, String mailjetApiKey, String mailjetApiSecret, String fromEmail,
                                             String fromName, String tenant) {
        this.id = id;
        this.mailjetApiKey = mailjetApiKey;
        this.mailjetApiSecret = mailjetApiSecret;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.tenant = tenant;
    }

    public static UpdateMailjetConfigurationCommand fromRequest(UUID id, UpdateMailjetConfigurationRequest request) {
        return new UpdateMailjetConfigurationCommand(id, request.getMailjetApiKey(), request.getMailjetApiSecret(),
                request.getEmail(), request.getName(), request.getTenant());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateMailjetConfigurationMessage();
    }
}
