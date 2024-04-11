package com.kynsof.rrhh.application.command.users.update;

import com.kynsof.rrhh.doman.dto.UserSystemDto;
import com.kynsof.rrhh.doman.dto.UserSystemImageDto;
import com.kynsof.rrhh.doman.interfaces.services.IUserSystemService;
import com.kynsof.rrhh.doman.rules.users.DeviceEmailValidateRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandHandler implements ICommandHandler<UpdateUserCommand> {

    private final IUserSystemService service;

    public UpdateUserCommandHandler(IUserSystemService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateUserCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "User.id", "User ID cannot be null."));
        UserSystemDto update = this.service.findById(command.getId());

        if (command.getImage() != null) {
            update.setImage(new UserSystemImageDto(UUID.randomUUID(), command.getImage()));
        }

        UpdateIfNotNull.updateIfStringNotNull(update::setIdentification, command.getIdentification());
        if (UpdateIfNotNull.updateIfStringNotNull(update::setEmail, command.getEmail())) {
            RulesChecker.checkRule(new DeviceEmailValidateRule(command.getEmail()));
        }
        UpdateIfNotNull.updateIfStringNotNull(update::setLastName, command.getLastName());
        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());

        service.update(update);
    }
}
