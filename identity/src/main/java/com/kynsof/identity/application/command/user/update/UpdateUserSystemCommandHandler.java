package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.application.command.auth.registry.UserRequest;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.service.IAuthService;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.domain.rules.usersystem.ModuleEmailMustBeUniqueRule;
import com.kynsof.identity.domain.rules.usersystem.ModuleUserNameMustBeUniqueRule;
import com.kynsof.identity.infrastructure.services.kafka.producer.user.ProducerUserSystemUpdateEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSystemCommandHandler implements ICommandHandler<UpdateUserSystemCommand> {

    private final IUserSystemService systemService;
    private final IAuthService keycloakProvider;
    private final ProducerUserSystemUpdateEventService resourceEventService;

    public UpdateUserSystemCommandHandler(IUserSystemService systemService, IAuthService keycloakProvider, ProducerUserSystemUpdateEventService resourceEventService) {
        this.systemService = systemService;
        this.keycloakProvider = keycloakProvider;
        this.resourceEventService = resourceEventService;
    }

    @Override
    public void handle(UpdateUserSystemCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "UserSystem ID cannot be null."));
        boolean isPublish = false;
        boolean idUpdate = false;
        UserSystemDto objectToUpdate = this.systemService.findById(command.getId());

        if (command.getEmail() != null && !command.getEmail().isEmpty() && !command.getEmail().equals(objectToUpdate.getEmail())) {
            UpdateIfNotNull.updateIfNotNull(objectToUpdate::setEmail, command.getEmail());
            RulesChecker.checkRule(new ModuleEmailMustBeUniqueRule(this.systemService, command.getEmail(), objectToUpdate.getId()));
            isPublish = true;
            idUpdate = true;
        }
        if (command.getName() != null && !command.getName().isEmpty() && !command.getName().equals(objectToUpdate.getName())) {
            UpdateIfNotNull.updateIfNotNull(objectToUpdate::setName, command.getName());
            isPublish = true;
            idUpdate = true;
        }
        if (command.getImage() != null && !command.getImage().isEmpty() && !command.getImage().equals(objectToUpdate.getImage())) {
            UpdateIfNotNull.updateIfNotNull(objectToUpdate::setImage, command.getImage());
            isPublish = true;
            idUpdate = true;
        }
        if (command.getLastName() != null && !command.getLastName().isEmpty() && !command.getLastName().equals(objectToUpdate.getLastName())) {
            UpdateIfNotNull.updateIfNotNull(objectToUpdate::setLastName, command.getLastName());
            isPublish = true;
            idUpdate = true;
        }

        if (command.getUserType() != null && command.getUserType() != objectToUpdate.getUserType()) {
            UpdateIfNotNull.updateIfNotNull(objectToUpdate::setUserType, command.getUserType());
            idUpdate = true;
        }

        if (command.getUserName() != null && !command.getUserName().isEmpty() && !command.getUserName().equals(objectToUpdate.getUserName())) {
            UpdateIfNotNull.updateIfNotNull(objectToUpdate::setUserName, command.getUserName());
            RulesChecker.checkRule(new ModuleUserNameMustBeUniqueRule(this.systemService, command.getUserName(), objectToUpdate.getId()));
            isPublish = true;
            idUpdate = true;
        }

        if (isPublish) {
            resourceEventService.create(objectToUpdate);
        }
        if (idUpdate) {
            updateUserKeycloak(command,objectToUpdate.getKeyCloakId().toString());
            systemService.update(objectToUpdate);

        }
    }

    private void updateUserKeycloak(UpdateUserSystemCommand command, String userKeycloakId) {


        UserRequest userRequest = new UserRequest(command.getUserName(), command.getEmail(), command.getName(), command.getLastName(), "");
        keycloakProvider.updateUser(userKeycloakId, userRequest);
    }
}
