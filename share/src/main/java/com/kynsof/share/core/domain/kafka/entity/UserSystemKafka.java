package com.kynsof.share.core.domain.kafka.entity;

import com.kynsof.share.core.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSystemKafka {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    List<String> roles;
    private String idImage;
    private UserType userType;
}
