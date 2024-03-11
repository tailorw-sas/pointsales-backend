package com.kynsof.identity.domain.interfaces;

import com.kynsof.identity.domain.dto.RolDto;

import java.util.UUID;

public interface IRoleService {
    UUID create(RolDto dto);
}
