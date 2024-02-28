package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.EQualificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDto implements Serializable {

    private UUID id;

    private String description;
    private String descriptionSoftDelete;
    private EQualificationStatus status;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private boolean deleted;

    public QualificationDto(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public QualificationDto(UUID id, String description, EQualificationStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

}