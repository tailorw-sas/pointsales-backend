package com.kynsof.identity.application.command.firebaseToken.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateFirebaseTokenMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_FIREBASE_TOKEN";

    public CreateFirebaseTokenMessage(UUID id) {
        this.id = id;
    }

}
