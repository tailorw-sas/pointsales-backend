package com.kynsof.identity.domain.interfaces.service;

import com.kynsof.identity.domain.dto.me.UserMeDto;

import java.util.UUID;

public interface IUserMeService {
    UserMeDto getUserInfo(UUID userId);
}
