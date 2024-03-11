package com.kynsof.identity.infrastructure.services;


import com.kynsof.identity.domain.dto.RolDto;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.identity.infrastructure.identity.RolSystem;
import com.kynsof.identity.infrastructure.repositories.command.RolWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repositories.query.RolReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RolServiceImpl implements IRoleService {

    @Autowired
    private RolWriteDataJPARepository repositoryCommand;

    @Autowired
    private RolReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(RolDto dto) {
        RolSystem allergy = this.repositoryCommand.save(new RolSystem(dto));
        return allergy.getId();
    }

    @Override
    public void update(RolDto roleUpdateDto) {
        if (roleUpdateDto == null || roleUpdateDto.getId() == null) {
            throw new IllegalArgumentException("Role DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(roleUpdateDto.getId())
                .map(role -> {
                    if (roleUpdateDto.getName() != null) role.setName(roleUpdateDto.getName());
                    if (roleUpdateDto.getDescription() != null) role.setDescription(roleUpdateDto.getDescription());

                    return this.repositoryCommand.save(role);
                })
                .orElseThrow(() -> new EntityNotFoundException("Role with ID " + roleUpdateDto.getId() + " not found"));
    }

//    @Override
//    public UUID update(Rol dto) {
//        if (dto == null || dto.getId() == null) {
//            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
//        }
//        Rol entity = this.repositoryCommand.save(dto);
//        return entity.getId();
//    }
//
//
//    @Override
//    public RolEntityDto findById(UUID id) {
//        Optional<Rol> contactInformation = this.repositoryQuery.findById(id);
//        if (contactInformation.isPresent()) {
//            return contactInformation.get().toAggregate();
//        }
//       // throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Contact Information not found.");
//        throw new RuntimeException("Patients not found.");
//    }
//
//    @Override
//    public PaginatedResponse findAll(Pageable pageable ) {
//        Page<Rol> data = this.repositoryQuery.findAll( pageable);
//        return getPaginatedResponse(data);
//    }
//
//    @Override
//    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
//        GenericSpecificationsBuilder<Rol> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
//        Page<Rol> data = this.repositoryQuery.findAll(specifications, pageable);
//
//        return getPaginatedResponse(data);
//    }
//
//    private PaginatedResponse getPaginatedResponse(Page<Rol> data) {
//        List<RolResponse> allergyResponses = new ArrayList<>();
//        for (Rol p : data.getContent()) {
//            allergyResponses.add(new RolResponse(p.toAggregate()));
//        }
//        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
//                data.getTotalElements(), data.getSize(), data.getNumber());
//    }
//
//    @Override
//    public void delete(UUID id) {
//        RolEntityDto contactInfoDto = this.findById(id);
//        contactInfoDto.setStatus(Status.INACTIVE);
//
//        this.repositoryCommand.save(new Rol(contactInfoDto));
//    }

}
