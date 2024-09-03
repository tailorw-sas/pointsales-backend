package com.kynsof.identity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseTokenDto {
    private UUID id;
    private UserSystemDto userSystemDto;
    private String token;

}
