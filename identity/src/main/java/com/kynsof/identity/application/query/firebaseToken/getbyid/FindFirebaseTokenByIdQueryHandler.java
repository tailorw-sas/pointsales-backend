package com.kynsof.identity.application.query.firebaseToken.getbyid;

import com.kynsof.identity.domain.dto.FirebaseTokenDto;
import com.kynsof.identity.domain.interfaces.service.IFirebaseTokenService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindFirebaseTokenByIdQueryHandler implements IQueryHandler<FindFirebaseTokenByIdQuery, FirebaseTokenResponse>  {

    private final IFirebaseTokenService service;

    public FindFirebaseTokenByIdQueryHandler(IFirebaseTokenService service) {
        this.service = service;
    }

    @Override
    public FirebaseTokenResponse handle(FindFirebaseTokenByIdQuery query) {
        FirebaseTokenDto response = service.findById(query.getId());

        return new FirebaseTokenResponse(response);
    }
}
