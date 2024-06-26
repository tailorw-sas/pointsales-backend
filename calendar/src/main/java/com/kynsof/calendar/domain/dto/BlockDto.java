package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
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
public class BlockDto implements Serializable {
    private UUID id;
    private String name;
    private EServiceStatus status;
    private String code;

}
