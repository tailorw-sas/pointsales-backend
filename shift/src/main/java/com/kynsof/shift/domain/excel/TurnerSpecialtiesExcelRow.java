package com.kynsof.shift.domain.excel;

import com.kynsof.share.core.application.excel.annotation.Cell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TurnerSpecialtiesExcelRow implements Serializable {
    @Cell(position = -1)
    private int rowNumber;
    @Cell(position = 0)
    private Date appoimentDate;
    @Cell(position = 1)
    private String medicalRecord;
    @Cell(position = 2)
    private String patient;
    @Cell(position = 3)
    private String identificationNumber;
    @Cell(position = 4)
    private String codDoctor;
    @Cell(position = 5)
    private String doctor;
    @Cell(position = 6)
    private String codSpecialties;
    @Cell(position = 7)
    private String specialties;


}
