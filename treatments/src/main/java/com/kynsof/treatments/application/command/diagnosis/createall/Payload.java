package com.kynsof.treatments.application.command.diagnosis.createall;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payload {
    private UUID idExternalConsultation;
    private List<DiagnosisRequest> diagnosis;
}
