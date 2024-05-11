package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.treatments.application.command.medicine.create.CreateMedicineRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PayloadMedicine {
    private List<CreateMedicineRequest> medicines;
}
