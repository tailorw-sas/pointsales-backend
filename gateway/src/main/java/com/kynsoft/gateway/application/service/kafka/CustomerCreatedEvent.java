package com.kynsoft.gateway.application.service.kafka;
import com.kynsoft.gateway.infrastructure.config.kafka.Event;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<RegisterDTO> {

}
