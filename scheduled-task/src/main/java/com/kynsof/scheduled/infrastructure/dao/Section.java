package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.scheduled.domain.ESectionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"service_id", "type"}))
public class Section {
    @Id
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private ESectionType type;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @JsonIgnoreProperties({"specialists", "sections"})
    private Service service;

}
