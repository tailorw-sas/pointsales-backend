package com.kynsof.identity.infrastructure.identity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_module")
@Getter
@Setter
@NoArgsConstructor
public class BusinessModule {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne()
    @JoinColumn(name = "module_id")
    private ModuleSystem module;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    public BusinessModule(Business business, ModuleSystem module) {
        this.business = business;
        this.module = module;
    }
}
