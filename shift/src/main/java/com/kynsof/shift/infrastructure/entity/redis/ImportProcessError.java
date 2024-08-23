package com.kynsof.shift.infrastructure.entity.redis;


import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.shift.domain.excel.TurnerSpecialtiesExcelRow;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

public class ImportProcessError {
    @Id
    private String id;
    private int rowNumber;
    @Indexed
    private String importProcessId;
    private List<ErrorField> errorFields;
    private TurnerSpecialtiesExcelRow row;
}
