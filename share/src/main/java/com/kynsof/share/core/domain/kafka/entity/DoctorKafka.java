package com.kynsof.share.core.domain.kafka.entity;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorKafka implements Serializable {

    private UUID id;
    private String identification;
    private String email;
    private String name;
    private String lastName;
    private String image;
}