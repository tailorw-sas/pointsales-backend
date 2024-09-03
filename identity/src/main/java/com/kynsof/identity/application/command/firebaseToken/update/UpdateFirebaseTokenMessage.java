package com.kynsof.identity.application.command.firebaseToken.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateFirebaseTokenMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_FIREBASE_TOKEN";

    public UpdateFirebaseTokenMessage(UUID id) {
        this.id = id;
    }

}
