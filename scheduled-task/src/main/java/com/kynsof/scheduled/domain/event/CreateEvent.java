package com.kynsof.scheduled.domain.event;

import com.kynsof.scheduled.infrastructure.config.kafka.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateEvent<T> {

    private T data;
    private EventType type;

    public CreateEvent() {
    }

}
