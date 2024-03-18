package com.kynsof.share.core.domain.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class UserKafka implements Serializable {

    private String id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String identification;
    private String phone;
    private String gender;
    private String status;

    public UserKafka() {
    }

}