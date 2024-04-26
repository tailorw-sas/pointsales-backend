package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.treatments.application.command.medicine.create.CreateMedicineRequest;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayloadMedicine {
    private List<CreateMedicineRequest> medicines;
}
