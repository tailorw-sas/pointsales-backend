package com.kynsof.identity.application.command.auth.firstsChangePassword;


import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.service.IAuthService;
import com.kynsof.identity.domain.interfaces.service.IRedisService;
import com.kynsof.identity.domain.interfaces.service.IUserSystemService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.UserNotFoundException;
import com.kynsof.share.core.domain.response.ErrorField;
import org.springframework.stereotype.Component;

@Component
public class FirstsChangePasswordCommandHandler implements ICommandHandler<FirstsChangePasswordCommand> {
    private final IAuthService authService;
    private final IUserSystemService userSystemService;
    private final IRedisService otpService;

    public FirstsChangePasswordCommandHandler(IAuthService authService, IUserSystemService userSystemService, IRedisService otpService) {

        this.authService = authService;
        this.userSystemService = userSystemService;
        this.otpService = otpService;
    }

    @Override
    public void handle(FirstsChangePasswordCommand command) {
        if (!otpService.getOtpCode(command.getEmail()).equals(command.getCodeOtp())) {
            throw new UserNotFoundException("User not found", new ErrorField("email/password", "Change Password not found"));
        }
        UserSystemDto userSystemDto = userSystemService.findByEmail(command.getEmail());
        if (userSystemDto != null) {
            Boolean result = authService.firstChangePassword(userSystemDto.getKeyCloakId().toString(), command.getEmail(),
                    command.getNewPassword(), command.getOldPassword());
            command.setResul(result);
        }

    }
}
