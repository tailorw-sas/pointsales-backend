package com.kynsof.calendar.application.query.firebaseToken.search;

import com.kynsof.calendar.domain.dto.FirebaseTokenDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class FirebaseTokenResponse implements IResponse {

    private UUID id;
    private String token;

    public FirebaseTokenResponse(FirebaseTokenDto object) {
        this.id = object.getId();
        this.token = object.getToken();
    }

}
