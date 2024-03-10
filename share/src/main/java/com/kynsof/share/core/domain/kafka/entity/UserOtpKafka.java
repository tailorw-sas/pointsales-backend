package com.kynsof.share.core.domain.kafka.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserOtpKafka implements Serializable {

    private String email;
    private String otpCode;

    public UserOtpKafka() {
    }

}