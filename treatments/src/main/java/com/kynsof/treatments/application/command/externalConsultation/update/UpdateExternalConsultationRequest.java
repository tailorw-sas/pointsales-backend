package com.kynsof.treatments.application.command.externalConsultation.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExternalConsultationRequest {

    private String identification;

    private String name;

    private String lastName;

    private String gender;
}
