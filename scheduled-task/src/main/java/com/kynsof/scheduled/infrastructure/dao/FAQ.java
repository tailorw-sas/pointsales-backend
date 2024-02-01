package com.kynsof.scheduled.infrastructure.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQ {
    @Id
    @GeneratedValue
    private UUID id;

    @Size(max = 500)
    private String question;

    @Size(max = 2000)
    private String answer;
}
