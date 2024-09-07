package com.kynsof.identity.application.command.firebaseToken.update;

import com.kynsof.identity.domain.dto.FirebaseTokenDto;
import com.kynsof.identity.domain.interfaces.service.IFirebaseTokenService;
import com.kynsof.identity.infrastructure.services.kafka.producer.firebaseToken.ProducerReplicateFirebaseTokenService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.FirebaseTokenKafka;
import org.springframework.stereotype.Component;

@Component
public class UpdateFirebaseTokenCommandHandler implements ICommandHandler<UpdateFirebaseTokenCommand> {

    private final IFirebaseTokenService service;
    private final ProducerReplicateFirebaseTokenService producerReplicateFirebaseTokenService;

    public UpdateFirebaseTokenCommandHandler(IFirebaseTokenService service,
                                             ProducerReplicateFirebaseTokenService producerReplicateFirebaseTokenService) {
        this.service = service;
        this.producerReplicateFirebaseTokenService = producerReplicateFirebaseTokenService;
    }

    @Override
    public void handle(UpdateFirebaseTokenCommand command) {

        FirebaseTokenDto firebaseTokenDto = service.findById(command.getId());
        firebaseTokenDto.setToken(command.getToken());
        service.update(firebaseTokenDto);
        this.producerReplicateFirebaseTokenService.create(new FirebaseTokenKafka(firebaseTokenDto.getId(), firebaseTokenDto.getToken()));
    }
}
