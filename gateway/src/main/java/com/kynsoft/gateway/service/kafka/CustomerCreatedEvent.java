package com.kynsoft.gateway.service.kafka;

import com.kynsoft.gateway.config.kafka.Event;
import com.kynsoft.gateway.dto.RegisterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<RegisterDTO> {

}
