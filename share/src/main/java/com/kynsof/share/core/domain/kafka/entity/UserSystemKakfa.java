package com.kynsof.share.core.domain.kafka.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSystemKakfa {
    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    List<String> roles;
    private UUID idImage;
}
