package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSystemCommandHandler implements ICommandHandler<UpdateUserSystemCommand> {

    private final IUserSystemService systemService;

    public UpdateUserSystemCommandHandler(IUserSystemService allergyService) {
        this.systemService = allergyService;
    }

    @Override
    public void handle(UpdateUserSystemCommand command) {
        systemService.update(new UserSystemDto(command.getId(), command.getUserName(),
                command.getEmail(), command.getName(), command.getLastName(), command.getStatus(),
                null));
    }
}
