package com.kynsoft.gateway.application.command.auth.sendPasswordRecoveryOtp;


import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.gateway.application.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class SendPasswordRecoveryOtpCommandHandler implements ICommandHandler<SendPasswordRecoveryOtpCommand> {
    private final AuthService authService;

    public SendPasswordRecoveryOtpCommandHandler(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void handle(SendPasswordRecoveryOtpCommand command) {
        Boolean result = authService.sendPasswordRecoveryOtp(command.getEmail());
        command.setResul(result);
    }
}
