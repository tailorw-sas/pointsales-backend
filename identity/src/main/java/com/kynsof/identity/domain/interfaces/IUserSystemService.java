package com.kynsof.identity.domain.interfaces;

import com.kynsof.identity.domain.dto.UserSystemDto;

import java.util.UUID;

public interface IUserSystemService {
    UUID create(UserSystemDto userSystemDto);
}
