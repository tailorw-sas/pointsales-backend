package com.kynsof.share.core.domain.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PatientKafka implements Serializable {

    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private String status;
    private UUID logo;
}
