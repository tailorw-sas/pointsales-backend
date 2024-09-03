package com.kynsof.identity.application.command.firebaseToken.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateFirebaseTokenCommand implements ICommand {

    private UUID id;
    private UUID userId;
    private String token;

    public CreateFirebaseTokenCommand(UUID userId, String token) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.token = token;
    }

    public static CreateFirebaseTokenCommand fromRequest(CreateFirebaseTokenRequest request) {
        return new CreateFirebaseTokenCommand(
                request.getUserId(),
                request.getToken()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateFirebaseTokenMessage(id);
    }
}
