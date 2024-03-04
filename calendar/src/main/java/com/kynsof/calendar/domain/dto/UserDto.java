package com.kynsof.calendar.domain.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto implements Serializable {

    private UUID id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public UserDto() {
    }

}