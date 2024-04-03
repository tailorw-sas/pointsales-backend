package com.kynsof.identity.application.command.role.update;

import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.interfaces.service.IRoleService;
import com.kynsof.identity.infrastructure.services.kafka.producer.ProducerUpdateRoleEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoleCommandHandler implements ICommandHandler<UpdateRoleCommand> {

    private final IRoleService roleService;

    @Autowired
    private ProducerUpdateRoleEventService updateRoleEventService;

    public UpdateRoleCommandHandler(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void handle(UpdateRoleCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule(command.getId(), "Role.id", "Role ID cannot be null."));

        RoleDto update = roleService.findById(command.getId());

        update.setDescription(command.getDescription() != null ? command.getDescription() : update.getDescription());
        update.setName(command.getName() != null ? command.getName() : update.getName());
        update.setStatus(command.getStatus());

        roleService.update(update);
        updateRoleEventService.update(update);
    }
}
