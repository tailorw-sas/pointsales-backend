package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.store.domain.dto.OrderDetailDto;
import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.store.domain.services.IOrderDetailService;
import com.kynsof.store.infrastructure.entity.Category;
import com.kynsof.store.infrastructure.entity.OrderDetail;
import com.kynsof.store.infrastructure.repositories.command.CategoryWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.command.OrderDetailWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.CategoryReadDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.OrderDetailReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class OrderDetailServiceImpl implements IOrderDetailService {

    private final OrderDetailWriteDataJPARepository repositoryCommand;
    private final OrderDetailReadDataJPARepository repositoryQuery;

    public OrderDetailServiceImpl(OrderDetailWriteDataJPARepository repositoryCommand,
                                  OrderDetailReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public UUID create(OrderDetailDto orderDetailDto) {
        OrderDetail save = this.repositoryCommand.save(new OrderDetail(orderDetailDto));
        return save.getId();
    }

    @Override
    public UUID update(OrderDetailDto orderDetailDto) {

        if (orderDetailDto == null || orderDetailDto.getId() == null) {
            throw new IllegalArgumentException("OrderDetail cannot be null");
        }

        return repositoryQuery.findById(orderDetailDto.getId())
                .map(orderDetail -> {
                    if (orderDetailDto.getProductId() != null)
                        orderDetail.setProduct(orderDetailDto.getProduct());
                    if (orderDetailDto.getQuantity() != null)
                        orderDetail.setQuantity(orderDetailDto.getQuantity());
                    if (orderDetailDto.getPrice() != null)
                        orderDetail.setPrice(orderDetailDto.getPrice());
                    return repositoryCommand.save(orderDetail);
                })
                .orElseThrow(() -> new EntityNotFoundException("OrderDetail with ID " + orderDetailDto.getId() + " not found"))
                .getId();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public OrderDetailDto findById(UUID id) {
        Optional<OrderDetail> orderDetail = this.repositoryQuery.findById(id);
        if (orderDetail.isPresent()) {
            return orderDetail.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "OrderDetail not found.");
    }
}
