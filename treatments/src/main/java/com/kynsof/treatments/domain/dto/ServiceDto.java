package com.kynsof.treatments.domain.dto;

import com.kynsof.treatments.domain.dto.enumDto.EServiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ServiceDto implements Serializable {
    private UUID id;
    private EServiceStatus status;
    private String picture;
    private String name;
    private String description;
    private String code;


    public ServiceDto(UUID id,  EServiceStatus status, String picture, String name,
                      String description, String code) {
        this.id = id;

        this.status = status;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.code = code;
    }


}
