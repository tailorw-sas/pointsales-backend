package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.enumTye.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserSystem implements Serializable {
    @Id
    @Column(name="id")
    private UUID id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String name;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRol> userRoles = new ArrayList<>();
//
//
//    public User(PatientDto patients) {
//        this.id = patients.getId();
//        this.identification = patients.getIdentification();
//        this.name = patients.getName();
//        this.lastName = patients.getLastName();
//        this.gender = patients.getGender();
//        this.status = patients.getStatus();
//    }
//
//    public User(DependentPatientDto patients) {
//        this.id = patients.getId();
//        this.identification = patients.getIdentification();
//        this.name = patients.getName();
//        this.lastName = patients.getLastName();
//        this.gender = patients.getGender();
//        this.status = patients.getStatus();
//        this.setPrime(new Patients(patients.getPrime()));
//    }
//
//    public PatientDto toAggregate() {
//        return new PatientDto(id, identification, name, lastName, gender, status);
//    }
}
