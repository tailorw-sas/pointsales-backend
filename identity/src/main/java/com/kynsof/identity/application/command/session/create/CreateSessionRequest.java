package com.kynsof.identity.application.command.session.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreateSessionRequest {
    private UUID userSystemId;
    private UUID businessId;
}
