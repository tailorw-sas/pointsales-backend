package com.kynsoft.service.kafka;

import com.kynsoft.config.kafka.Event;
import com.kynsoft.dto.RegisterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<RegisterDTO> {

}
