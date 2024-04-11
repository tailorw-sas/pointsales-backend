package com.kynsof.rrhh.infrastructure.identity;

import com.kynsof.rrhh.doman.dto.UserSystemImageDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user_system_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSystemImage implements Serializable {

    @Id
    private UUID id;

    @Column(nullable = true)
    private byte [] image;

    @OneToOne(mappedBy = "image")
    private UserSystem userSystem;

    public UserSystemImage(UserSystemImageDto dto) {
        this.id = dto.getId();
        this.image = dto.getImage();
    }

    public UserSystemImageDto toAggregate () {
        return new UserSystemImageDto(id, image, userSystem.toAggregate());
    }

}
