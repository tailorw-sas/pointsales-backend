package com.kynsof.rrhh.infrastructure.identity;

import com.kynsof.rrhh.domain.dto.DoctorDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor extends UserSystem {
    private String registerNumber;
    private String language;
    private boolean isExpress;

    public Doctor() {
        super();
    }

    public Doctor(DoctorDto dto) {
        super(dto);
        this.registerNumber = dto.getRegisterNumber();
        this.language = dto.getLanguage();
        this.isExpress = dto.isExpress();
    }

    @Override
    public DoctorDto toAggregate() {
        return new DoctorDto(
                this.getId(),
                this.getIdentification(),
                this.getEmail(),
                this.getName(),
                this.getLastName(),
                this.getStatus(),
                this.registerNumber,
                this.language,
                this.isExpress
        );
    }
}