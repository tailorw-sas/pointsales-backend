package com.kynsof.store.application.query.getAll;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.SupplierEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class SupplierResponse implements IResponse {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;

    public SupplierResponse(SupplierEntityDto supplierDto) {
        this.id = supplierDto.getId();
        this.name = supplierDto.getName();
        this.address = supplierDto.getAddress();
        this.phone = supplierDto.getPhone();
        this.email = supplierDto.getEmail();
    }
}
