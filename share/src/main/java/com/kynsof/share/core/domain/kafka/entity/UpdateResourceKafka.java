package com.kynsof.share.core.domain.kafka.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateResourceKafka {
    private UUID id;
    private String identification;
}