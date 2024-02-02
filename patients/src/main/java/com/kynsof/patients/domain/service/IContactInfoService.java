package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IContactInfoService {
     UUID create(ContactInfoDto patients);
    UUID update(ContactInfoDto patients);
     void delete(UUID id);
     ContactInfoDto findById(UUID id);
     PaginatedResponse findAll(Pageable pageable, UUID idPatients,  String email, String phone);
}