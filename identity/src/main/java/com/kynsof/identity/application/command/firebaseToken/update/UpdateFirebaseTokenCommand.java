package com.kynsof.identity.application.command.firebaseToken.update;

import com.kynsof.identity.application.command.firebaseToken.create.CreateFirebaseTokenRequest;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateFirebaseTokenCommand implements ICommand {

    private UUID id;
    private UUID userId;
    private String token;

    public UpdateFirebaseTokenCommand(UUID id, UUID userId, String token) {
        this.id = id;
        this.userId = userId;
        this.token = token;
    }

    public static UpdateFirebaseTokenCommand fromRequest(CreateFirebaseTokenRequest request, UUID id) {
        return new UpdateFirebaseTokenCommand(
                id, 
                request.getUserId(),
                request.getToken());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateFirebaseTokenMessage(id);
    }
}
