package com.kynsof.identity.application.query.paymentdev.getbyid;

import com.kynsof.identity.application.query.users.getSearch.UserSystemsResponse;
import com.kynsof.identity.domain.dto.*;
import com.kynsof.share.core.domain.bus.query.IResponse;
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
public class PaymentDevResponse implements IResponse {
    private UUID id;
    private UserSystemsResponse user;
    private Double payment;
    private String reference;
    private LocalDateTime createdAt;

    public PaymentDevResponse(PaymentDevDto dto) {
        this.id = dto.getId();
        this.user = dto.getUser() != null ? new UserSystemsResponse(dto.getUser()) : null;
        this.payment = dto.getPayment();
        this.reference = dto.getReference();
        this.createdAt = dto.getCreatedAt();
    }

}
