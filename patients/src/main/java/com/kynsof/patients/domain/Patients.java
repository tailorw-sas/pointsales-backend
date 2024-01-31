package com.kynsof.patients.domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Patients {
    
    private UUID id;

    private String identification;

    private String name;

    private String lastName;

    private String gender;
}
