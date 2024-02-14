package com.kynsof.scheduled.domain.event;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EBusinessStatus;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessKafka implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private String ruc;
    private EBusinessStatus status;

    public BusinessKafka(BusinessDto entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.ruc = entity.getRuc();
        this.status = entity.getStatus();
    }

}
