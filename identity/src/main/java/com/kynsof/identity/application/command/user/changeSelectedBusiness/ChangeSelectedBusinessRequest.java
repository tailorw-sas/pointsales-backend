package com.kynsof.identity.application.command.user.changeSelectedBusiness;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ChangeSelectedBusinessRequest {
    private UUID businessId;
}
