package com.kynsoft.gateway.application.command.user.update;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.domain.dto.user.UserRequest;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandHandler implements ICommandHandler<UpdateUserCommand> {
    private final IUserService userService;

    public UpdateUserCommandHandler(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(UpdateUserCommand command) {
        UserRequest userRequest = new UserRequest(
                command.getUsername(),
                command.getEmail(),
                command.getFirstname(),
                command.getLastname(),
                command.getPassword());
        userService.updateUser(command.getUserId(), userRequest);
        command.setResul(true);
    }
}
