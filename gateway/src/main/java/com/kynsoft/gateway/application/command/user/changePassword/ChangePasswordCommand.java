package com.kynsoft.gateway.application.command.user.changePassword;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordCommand implements ICommand {
    private Boolean resul;
    private final String userId;
    private final String newPassword;
    private final String oldPassword;


    public ChangePasswordCommand(String userId, String newPassword, String otp) {

        this.userId = userId;
        this.newPassword = newPassword;
        this.oldPassword = otp;
    }

    @Override
    public ICommandMessage getMessage() {
        return new ChangePasswordMessage(resul);
    }
}
