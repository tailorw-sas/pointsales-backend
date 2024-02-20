package com.kynsof.share.core.domain.kafka.event;

import com.kynsof.share.core.domain.kafka.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateEvent<T> {

    private T data;
    private EventType type;

    public UpdateEvent() {
    }

}
