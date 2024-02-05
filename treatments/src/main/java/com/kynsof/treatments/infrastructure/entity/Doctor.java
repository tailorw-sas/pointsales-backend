package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Doctor {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(unique = true)
    private String identification;

    private String name;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String specialty;

}
