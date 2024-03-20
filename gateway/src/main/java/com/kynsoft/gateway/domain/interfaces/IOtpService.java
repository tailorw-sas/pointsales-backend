package com.kynsoft.gateway.domain.interfaces;

public interface IOtpService {
    void saveOtpCode(String email, String otpCode);
    String getOtpCode(String email);
    String generateOtpCode();
}
