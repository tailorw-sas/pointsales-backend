package com.kynsof.calendar.domain.event;

import com.kynsof.calendar.infrastructure.config.kafka.EventType;
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
