package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.users.getall.UserSystemsResponse;
import com.kynsof.identity.domain.dto.Status;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repositories.command.UserSystemsWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repositories.query.UserSystemReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Override
    public void update(UserSystemDto userSystemDto) {
        if (userSystemDto == null || userSystemDto.getId() == null) {
            throw new IllegalArgumentException("UserSystem DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(userSystemDto.getId())
                .ifPresent(userSystem -> { // Cambia .map a .ifPresent para actuar solo si el objeto está presente.
                    if (userSystemDto.getUserName() != null) {
                        userSystem.setUserName(userSystemDto.getUserName());
                    }
                    if (userSystemDto.getEmail() != null) {
                        userSystem.setEmail(userSystemDto.getEmail());
                    }
                    if (userSystemDto.getName() != null) {
                        userSystem.setName(userSystemDto.getName());
                    }
                    if (userSystemDto.getLastName() != null) {
                        userSystem.setLastName(userSystemDto.getLastName());
                    }
                    if (userSystemDto.getStatus() != null) {
                        userSystem.setStatus(userSystemDto.getStatus());
                    }


                    this.repositoryCommand.save(userSystem);
                });
    }


    @Override
    public void delete(UUID id) {
       UserSystemDto userSystemDto  = this.findById(id);
        userSystemDto.setStatus(Status.INACTIVE);

        this.repositoryCommand.save(new UserSystem(userSystemDto));
    }

    @Override
    public UserSystemDto findById(UUID id) {
        Optional<UserSystem> userSystem = this.repositoryQuery.findById(id);
        if (userSystem.isPresent()) {
            return userSystem.get().toAggregate();
        }
        throw new RuntimeException("User not found.");
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<UserSystem> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<UserSystem> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<UserSystem> data) {
        List<UserSystemsResponse> allergyResponses = new ArrayList<>();
        for (UserSystem p : data.getContent()) {
            allergyResponses.add(new UserSystemsResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
