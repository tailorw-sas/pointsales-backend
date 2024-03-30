package com.kynsoft.notification.application.command.mailjetConfiguration.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.service.IMailjetConfigurationService;
import org.springframework.stereotype.Component;

@Component
public class DeleteMailjetConfigurationCommandHandler implements ICommandHandler<DeleteMailjetConfigurationCommand> {

    private final IMailjetConfigurationService serviceImpl;

    public DeleteMailjetConfigurationCommandHandler(IMailjetConfigurationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteMailjetConfigurationCommand command) {

        serviceImpl.delete(command.getId());
    }

}
