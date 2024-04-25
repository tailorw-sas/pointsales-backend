package com.kynsof.identity.application.command.user.update.steptwo;

import com.kynsof.share.core.domain.UserType;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserSystemStepTwoRequest {
    private UUID id;
    private byte [] image;
    private UserType userType;
}
