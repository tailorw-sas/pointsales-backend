package com.kynsof.calendar.domain.event;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.EBusinessStatus;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessKafka implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private String ruc;
    private EBusinessStatus status;

    public BusinessKafka() {
    }

    public BusinessKafka(BusinessDto entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.ruc = entity.getRuc();
        this.status = entity.getStatus();
    }

    @Override
    public String toString() {
        return "BusinessKafka{" + "id=" + id + ", name=" + name + ", description=" + description + ", ruc=" + ruc + ", status=" + status + '}';
    }

}
