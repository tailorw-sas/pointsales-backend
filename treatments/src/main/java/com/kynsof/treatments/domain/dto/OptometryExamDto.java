package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptometryExamDto {
    private UUID id;
    private  String sphereOd;
    private  String cylinderOd;
    private  String axisOd;
    private  String avscOd;
    private  String avccOd;
    private  String sphereOi;
    private  String cylinderOi;
    private  String axisOi;
    private  String avscOi;
    private  String avccOi;
    private  String addPower;
    private  String dp;
    private  String dv;
    private  String filter;
    private  boolean isCurrent;

    public OptometryExamDto(String sphereOd, String cylinderOd, String axisOd, String avscOd, String avccOd, String sphereOi, String cylinderOi, String axisOi, String avscOi, String avccOi, String addPower, String dp, String dv, String filter, boolean isCurrent) {
        this.sphereOd = sphereOd;
        this.cylinderOd = cylinderOd;
        this.axisOd = axisOd;
        this.avscOd = avscOd;
        this.avccOd = avccOd;
        this.sphereOi = sphereOi;
        this.cylinderOi = cylinderOi;
        this.axisOi = axisOi;
        this.avscOi = avscOi;
        this.avccOi = avccOi;
        this.addPower = addPower;
        this.dp = dp;
        this.dv = dv;
        this.filter = filter;
        this.isCurrent = isCurrent;
    }
}