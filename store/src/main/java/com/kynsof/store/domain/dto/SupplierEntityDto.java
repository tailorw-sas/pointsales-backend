package com.kynsof.store.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SupplierEntityDto {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;
}
