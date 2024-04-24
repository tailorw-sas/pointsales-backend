package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Wallet wallet;

    public Customer(CustomerDto customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
    }

    public CustomerDto toAggregate () {
        return new CustomerDto(
                id, 
                firstName, 
                lastName, 
                email
        );
    }

}