package com.kynsof.identity.application.command.walletTransaction.create;

import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IGeographicLocationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletTransactionCommandHandler implements ICommandHandler<CreateWalletTransactionCommand> {

    private final IBusinessService service;

    @Autowired
    private ProducerSaveFileEventService saveFileEventService;

    @Autowired
    private IGeographicLocationService geographicLocationService;

    public CreateWalletTransactionCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateWalletTransactionCommand command) {

    }
}