package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.BlockResponse;
import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.calendar.infrastructure.entity.Block;
import com.kynsof.calendar.infrastructure.repository.command.BlockWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BlockReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlockServiceImpl implements IBlockService {

    private final BlockWriteDataJPARepository repositoryCommand;

    private final BlockReadDataJPARepository repositoryQuery;

    public BlockServiceImpl(BlockWriteDataJPARepository repositoryCommand, BlockReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public UUID create(BlockDto object) {
        return this.repositoryCommand.save(new Block(object)).getId();
    }

    @Override
    public void update(BlockDto objectDto) {
        Block update = new Block(objectDto);
        this.repositoryCommand.save(update);
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    private BlockDto getById(UUID id) {

        Optional<Block> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_TYPE_NOT_FOUND, new ErrorField("id", "Service Type not found.")));

    }

    //@Cacheable(cacheNames = CacheConfig.SERVICE_CACHE, unless = "#result == null")
    @Override
    public BlockDto findById(UUID id) {

        Optional<Block> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_TYPE_NOT_FOUND, new ErrorField("id", "Service Type not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCriteria(filterCriteria);
        GenericSpecificationsBuilder<Block> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Block> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private void filterCriteria(List<FilterCriteria> filterCriteria) {
        filterCriteria.forEach(filter -> {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                filter.setValue(parseEnum(EServiceStatus.class, (String) filter.getValue(), "EServiceStatus"));
            }
        });
    }
    private <T extends Enum<T>> T parseEnum(Class<T> enumClass, String value, String enumName) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid value for enum " + enumName + ": " + value);
            return null;
        }
    }
    private PaginatedResponse getPaginatedResponse(Page<Block> data) {
        List<BlockResponse> patients = new ArrayList<>();
        for (Block o : data.getContent()) {
            patients.add(new BlockResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


}
