package com.kynsof.identity.domain.interfaces.service;

public interface IOtpService {
    void saveOtpCode(String email, String otpCode);
    String getOtpCode(String email);
    String generateOtpCode();
}
