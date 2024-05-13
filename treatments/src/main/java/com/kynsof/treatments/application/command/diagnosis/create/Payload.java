package com.kynsof.treatments.application.command.diagnosis.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class Payload {
    private UUID externalConsultation;
    private List<DiagnosisRequest> diagnosis;
}
