package com.kynsoft.rrhh.application.command.registerFingerprint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RegisterFingerprintRequest {
    private UUID userSystemId;
    private byte[] registerFingerprint;
    private UUID  equipmentId;
}
