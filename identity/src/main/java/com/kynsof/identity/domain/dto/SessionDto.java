package com.kynsof.identity.domain.dto;

import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SessionDto {
    private UUID id;
    private UserSystemDto userSystemDto;
    private ESessionStatus status;
    private BusinessDto businessDto;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private boolean deleted;

    public SessionDto(UUID id, UserSystemDto userSystemDto,
                      ESessionStatus status, BusinessDto businessDto) {
        this.id = id;
        this.userSystemDto = userSystemDto;
        this.status = status;
        this.businessDto = businessDto;
    }

}

