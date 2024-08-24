package com.kynsof.shift.domain.excel;

import com.kynsof.share.core.application.excel.CustomCellType;
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
    @Cell(position = 0,cellType = CustomCellType.DATE)
    private Date appoimentDate;
    @Cell(position = 1,cellType = CustomCellType.DATAFORMAT)
    private String medicalRecord;
    @Cell(position = 2,cellType = CustomCellType.DATAFORMAT)
    private String patient;
    @Cell(position = 3,cellType = CustomCellType.DATAFORMAT)
    private String identificationNumber;
    @Cell(position = 4,cellType = CustomCellType.DATAFORMAT)
    private String codDoctor;
    @Cell(position = 5,cellType = CustomCellType.DATAFORMAT)
    private String doctor;
    @Cell(position = 6,cellType = CustomCellType.DATAFORMAT)
    private String codSpecialties;
    @Cell(position = 7,cellType = CustomCellType.DATAFORMAT)
    private String specialties;


}
