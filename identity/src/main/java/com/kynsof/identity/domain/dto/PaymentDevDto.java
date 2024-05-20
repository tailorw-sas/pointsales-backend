package com.kynsof.identity.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDevDto {
    private UUID id;
    private UserSystemDto user;
    private Double payment;
    private String reference;
    private LocalDateTime createdAt;

    public PaymentDevDto(UUID id, UserSystemDto user, Double payment, String reference) {
        this.id = id;
        this.user = user;
        this.payment = payment;
        this.reference = reference;
    }

}
