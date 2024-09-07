package com.kynsof.share.core.domain.kafka.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseTokenKafka implements Serializable {
    private UUID id;
    private String token;
}
