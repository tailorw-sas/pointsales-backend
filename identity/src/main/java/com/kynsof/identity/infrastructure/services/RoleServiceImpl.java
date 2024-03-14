package com.kynsof.identity.infrastructure.services;


import com.kynsof.identity.application.query.roles.getSearch.RoleSystemsResponse;
import com.kynsof.identity.domain.dto.RoleDto;
import com.kynsof.identity.domain.dto.RoleStatusEnm;
import com.kynsof.identity.domain.interfaces.IRoleService;
import com.kynsof.identity.infrastructure.identity.RoleSystem;
import com.kynsof.identity.infrastructure.repository.command.RolWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.RolReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RolWriteDataJPARepository repositoryCommand;

    @Autowired
    private RolReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(RoleDto dto) {
        RoleSystem allergy = this.repositoryCommand.save(new RoleSystem(dto));
        return allergy.getId();
    }

    @Override
    public void update(RoleDto roleUpdateDto) {
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

    @Override
    public void delete(UUID id) {

    }

    @Override
    public RoleDto findById(UUID id) {
        Optional<RoleSystem> rolSystem = this.repositoryQuery.findById(id);
        if (rolSystem.isPresent()) {
            return rolSystem.get().toAggregate();
        }
        throw new RuntimeException("RolSystem not found.");
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        UserSystemServiceImpl.filterCreteria(filterCriteria);

        GenericSpecificationsBuilder<RoleSystem> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<RoleSystem> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<RoleSystem> data) {
        List<RoleSystemsResponse> allergyResponses = new ArrayList<>();
        for (RoleSystem p : data.getContent()) {
            allergyResponses.add(new RoleSystemsResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


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
