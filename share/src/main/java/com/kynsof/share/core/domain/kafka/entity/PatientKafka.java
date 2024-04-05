package com.kynsof.share.core.domain.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PatientKafka implements Serializable {

    private String id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private String status;
    private String logo;
}
