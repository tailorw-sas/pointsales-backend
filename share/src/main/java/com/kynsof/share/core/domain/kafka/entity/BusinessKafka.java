package com.kynsof.share.core.domain.kafka.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BusinessKafka implements Serializable {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("ruc")
    private String ruc;
    @JsonProperty("status")
    private String status;

    public BusinessKafka() {
    }

}
