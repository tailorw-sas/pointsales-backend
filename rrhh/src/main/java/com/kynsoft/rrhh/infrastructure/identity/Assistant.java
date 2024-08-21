package com.kynsoft.rrhh.infrastructure.identity;

import com.kynsoft.rrhh.domain.dto.AssistantDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Assistant extends UserSystem {
    private String department;

    public Assistant() {
        super();
    }

    public Assistant(AssistantDto dto) {
        super(dto);
        this.department = dto.getDepartment();
    }

    @Override
    public AssistantDto toAggregate() {
        return new AssistantDto(
                this.getId(),
                this.getIdentification(),
                this.getCode(),
                this.getEmail(),
                this.getName(),
                this.getLastName(),
                this.getStatus(),
                this.getPhoneNumber(),
                this.getImage(),
                this.department
        );
    }
}