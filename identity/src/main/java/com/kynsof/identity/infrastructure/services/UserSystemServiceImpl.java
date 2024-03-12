package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repositories.command.UserSystemsWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repositories.query.UserSystemReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserSystemServiceImpl implements IUserSystemService {
    @Autowired
    private UserSystemsWriteDataJPARepository repositoryCommand;

    @Autowired
    private UserSystemReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(UserSystemDto userSystemDto) {
        UserSystem data = new UserSystem(userSystemDto);
        UserSystem userSystem = this.repositoryCommand.save(data);
        return userSystem.getId();
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
