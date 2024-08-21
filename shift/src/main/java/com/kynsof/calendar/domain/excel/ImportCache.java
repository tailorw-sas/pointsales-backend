package com.kynsof.calendar.domain.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "importcache",timeToLive = 14400)
public class ImportCache implements Serializable {
    @Id
    private String id;
    @Indexed
    private String importProcessId;
    private int rowNumber;

    private Date appoimentDate;

    private String medicalRecord;
    private String patient;

    private String identificationNumber;
    private String codDoctor;
    private String doctor;
    private String codSpecialties;
    private String specialties;



}
