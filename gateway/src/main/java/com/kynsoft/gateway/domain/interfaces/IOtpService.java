package com.kynsoft.gateway.domain.interfaces;

public interface IOtpService {
    Boolean saveOtpCode(String email, String otpCode);
    String getOtpCode(String email);
    // Método para generar un nuevo código OTP de 6 dígitos
    String generateOtpCode();
}
