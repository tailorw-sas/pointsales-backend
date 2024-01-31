package com.kynsof.patients.infrastructure.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ContactInformationDAO {
    @Id
    @Column(name="patients_id")
    private UUID id;

    private String email;

    private String telephone;

    private String address;

    private LocalDate birthdayDate;

}
