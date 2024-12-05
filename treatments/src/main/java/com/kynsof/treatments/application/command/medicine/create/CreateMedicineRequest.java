package com.kynsof.treatments.application.command.medicine.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMedicineRequest {
    private String name;
    private String presentation;
}
