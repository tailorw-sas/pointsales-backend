package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "calendar_user")
public class User {

    @Id
    private UUID id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public User(UserDto user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }

    public UserDto toAggregate () {
        return new UserDto(id, username, email, firstname, lastname);
    }

}