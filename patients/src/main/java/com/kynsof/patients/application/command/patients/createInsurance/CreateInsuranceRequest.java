package com.kynsof.patients.application.command.patients.createInsurance;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateInsuranceRequest {
   private List<UUID> insuranceIds;
    private UUID patientId;



}
