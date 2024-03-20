package com.kynsoft.gateway.application.command.auth.forwardPassword;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.application.dto.PasswordChangeRequest;
import com.kynsoft.gateway.application.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class ForwardPasswordCommandHandler implements ICommandHandler<ForwardPasswordCommand> {
    private final AuthService authService;

    public ForwardPasswordCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(ForwardPasswordCommand command) {
        Boolean result = authService.forwardPassword(new PasswordChangeRequest(command.getEmail(),
                command.getNewPassword(), command.getOtp()));
        command.setResul(result);
    }
}
