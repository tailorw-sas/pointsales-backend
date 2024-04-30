package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.application.command.auth.registrySystemUser.UserSystemRequest;
import com.kynsof.identity.application.command.auth.autenticate.LoginRequest;
import com.kynsof.identity.application.command.auth.autenticate.TokenResponse;
import com.kynsof.identity.application.command.auth.forwardPassword.PasswordChangeRequest;
import com.kynsof.identity.application.command.auth.registry.UserRequest;
import io.micrometer.common.lang.NonNull;

public interface IAuthService {
    TokenResponse authenticate(LoginRequest loginDTO);
    TokenResponse refreshToken(String refreshToken);
    String registerUser(@NonNull UserRequest userRequest, boolean isSystemUser);
    String registerUserSystem(@NonNull UserSystemRequest userRequest, boolean isSystemUser);
    Boolean forwardPassword(PasswordChangeRequest changeRequest);
    Boolean sendPasswordRecoveryOtp(String email);
}