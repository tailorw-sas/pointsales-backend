package com.kynsoft.gateway.application.service.kafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends CreateEvent<RegisterDTO> {

}
