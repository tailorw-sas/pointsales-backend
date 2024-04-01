package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.ModuleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SystemModule {

    @Id
    @Column(name = "id")
    protected UUID id;
    private String name;
    private byte[] image;
    private String description;

    public SystemModule(ModuleDto module) {
        this.id = module.getId();
        this.name = module.getName();
        this.image = module.getImage();
        this.description = module.getDescription();
    }

    public ModuleDto toAggregate () {
        return new ModuleDto(id, name, image, description);
    }
}
