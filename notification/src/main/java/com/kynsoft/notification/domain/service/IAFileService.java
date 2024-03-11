package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.domain.dto.AFileDto;
import java.util.UUID;

public interface IAFileService {
    void create(AFileDto object);
    void update(AFileDto object);
    void delete(UUID id);
    AFileDto findById(UUID id);
}
