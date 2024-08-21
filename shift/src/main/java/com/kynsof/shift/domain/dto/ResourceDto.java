package com.kynsof.shift.domain.dto;

import com.kynsof.shift.domain.dto.enumType.EResourceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceDto implements Serializable {

    private UUID id;
    private String name;
    private String image;
    private EResourceStatus status;
    private String code;

}
