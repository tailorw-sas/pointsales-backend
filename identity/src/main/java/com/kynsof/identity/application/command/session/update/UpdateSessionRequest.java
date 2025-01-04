package com.kynsof.identity.application.command.session.update;

import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateSessionRequest {
    private UUID userSystemId;
    private ESessionStatus status;
    private UUID businessId;
}