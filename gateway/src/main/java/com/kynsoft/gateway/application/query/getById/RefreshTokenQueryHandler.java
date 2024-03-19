package com.kynsoft.gateway.application.query.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.gateway.application.dto.TokenResponse;
import com.kynsoft.gateway.application.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenQueryHandler implements IQueryHandler<RefreshTokenQuery, TokenResponse>  {

    private final AuthService serviceImpl;

    public RefreshTokenQueryHandler(AuthService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public TokenResponse handle(RefreshTokenQuery query) {

        return serviceImpl.refreshToken(query.getRefreshToken());
    }
}
