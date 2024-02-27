package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.enumType.EQualificationStatus;
import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class QualificationResponse implements IResponse {
    private UUID id;
    private String description;
    private EQualificationStatus status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public QualificationResponse(QualificationDto qualification) {
        this.id = qualification.getId();
        this.description = qualification.getDescription();
        this.status = qualification.getStatus();
        this.createAt = qualification.getCreateAt();
        this.updateAt = qualification.getUpdateAt();
        this.deleteAt = qualification.getDeleteAt();
    }

}