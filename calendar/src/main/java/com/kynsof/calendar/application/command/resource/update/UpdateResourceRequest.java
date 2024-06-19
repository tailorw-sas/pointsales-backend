package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateResourceRequest {
    private String name;
    private String image;
    private EResourceStatus status;
    private List<UUID> serviceIds;
}