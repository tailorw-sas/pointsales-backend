package com.kynsof.identity.application.command.user.update;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.identity.infrastructure.services.KeycloakProvider;
import com.kynsof.identity.infrastructure.services.kafka.producer.ProducerResourceEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.UserType;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSystemCommandHandler implements ICommandHandler<UpdateUserSystemCommand> {

    private final IUserSystemService systemService;
    private final KeycloakProvider keycloakProvider;
    private final ProducerResourceEventService resourceEventService;

    public UpdateUserSystemCommandHandler(IUserSystemService systemService, KeycloakProvider keycloakProvider, ProducerResourceEventService resourceEventService) {
        this.systemService = systemService;
        this.keycloakProvider = keycloakProvider;
        this.resourceEventService = resourceEventService;
    }

    @Override
    public void handle(UpdateUserSystemCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "UserSystem ID cannot be null."));
        UserSystemDto objectToUpdate = this.systemService.findById(command.getId());

        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setEmail, command.getEmail());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setLastName, command.getLastName());
        UpdateIfNotNull.updateIfNotNull(objectToUpdate::setName, command.getName());
        objectToUpdate.setUserType(command.getUserType());
        objectToUpdate.setImage(command.getImage());
        objectToUpdate.setUserName(command.getUserName());

        updateUserKeycloak(command);
        systemService.update(objectToUpdate);
        if (command.getUserType().equals(UserType.DOCTORS) ||
                command.getUserType().equals(UserType.ASSISTANTS) ||
                command.getUserType().equals(UserType.NURSES)) {
            resourceEventService.create(objectToUpdate);
        }
    }

    private void updateUserKeycloak(UpdateUserSystemCommand userRequest) {
        try {
            UserResource userResource = keycloakProvider.getUserResource().get(userRequest.getId().toString());
            UserRepresentation user = userResource.toRepresentation();
            if (userRequest.getUserName() != null) {
                user.setUsername(userRequest.getUserName());
            }
            if (userRequest.getName() != null) {
                user.setFirstName(userRequest.getName());
            }
            if (userRequest.getLastName() != null) {
                user.setLastName(userRequest.getLastName());
            }
            if (userRequest.getEmail() != null) {
                user.setEmail(userRequest.getEmail());
                user.setEmailVerified(true);
            }
            user.setEnabled(true);
            userResource.update(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user.", e);
        }
    }
}
