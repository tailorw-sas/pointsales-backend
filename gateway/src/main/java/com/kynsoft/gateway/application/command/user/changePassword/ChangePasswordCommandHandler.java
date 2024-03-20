package com.kynsoft.gateway.application.command.user.changePassword;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordCommandHandler implements ICommandHandler<ChangePasswordCommand> {
    private final IUserService userService;

    public ChangePasswordCommandHandler(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(ChangePasswordCommand command) {
        Boolean result = userService.changeUserPassword(command.getUserId(), command.getOldPassword(), command.getNewPassword());
        command.setResul(result);
    }
}
