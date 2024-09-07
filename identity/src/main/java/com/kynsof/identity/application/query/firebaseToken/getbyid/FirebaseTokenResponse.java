package com.kynsof.identity.application.query.firebaseToken.getbyid;

import com.kynsof.identity.application.query.users.getSearch.UserSystemsResponse;
import com.kynsof.identity.domain.dto.FirebaseTokenDto;
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
    private UserSystemsResponse userSystem;
    private String token;

    public FirebaseTokenResponse(FirebaseTokenDto object) {
        this.id = object.getId();
        this.userSystem = new UserSystemsResponse(object.getUserSystemDto());
        this.token = object.getToken();

    }


}