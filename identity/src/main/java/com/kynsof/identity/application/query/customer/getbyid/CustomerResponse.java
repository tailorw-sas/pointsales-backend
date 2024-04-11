package com.kynsof.identity.application.query.customer.getbyid;

import com.kynsof.identity.domain.dto.CustomerDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerResponse implements IResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    public CustomerResponse(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
    }

}