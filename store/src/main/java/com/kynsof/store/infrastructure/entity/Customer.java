package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.CustomerDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.phone = customerDto.getPhone();
    }

    public CustomerDto toAggregate() {
        return new CustomerDto(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.phone
        );
    }
}
