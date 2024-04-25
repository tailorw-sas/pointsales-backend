package com.kynsof.treatments.application.command.diagnosis.createall;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payload {
    private List<DiagnosisRequest> payload;
}
