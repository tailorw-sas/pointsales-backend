package com.kynsof.identity.application.command.user.update.steptwo;

import com.kynsof.identity.domain.dto.enumType.UserType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserSystemStepTwoCommand implements ICommand {
    private UUID id;
    private byte [] image;
    private UserType userType;

    public UpdateUserSystemStepTwoCommand(UUID id, byte [] image, UserType userType) {
        this.id = id;
        this.image = image;
        this.userType = userType;
    }

    public static UpdateUserSystemStepTwoCommand fromRequest(UpdateUserSystemStepTwoRequest request) {
        return new UpdateUserSystemStepTwoCommand(
                request.getId(),
                request.getImage(),
                request.getUserType()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateUserSystemStepTwoMessage(id);
    }
}
