package com.kynsof.store.application.query.customer.getAll;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CustomerResponse implements IResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public CustomerResponse(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.phone = customerDto.getPhone();
    }
}