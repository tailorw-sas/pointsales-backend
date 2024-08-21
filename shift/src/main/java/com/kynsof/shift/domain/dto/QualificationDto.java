package com.kynsof.shift.domain.dto;

import com.kynsof.shift.domain.dto.enumType.EQualificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDto implements Serializable {

    private UUID id;

    private String description;
    private EQualificationStatus status;

    public QualificationDto(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

}