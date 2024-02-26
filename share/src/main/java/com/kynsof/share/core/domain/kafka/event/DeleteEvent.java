package com.kynsof.share.core.domain.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteEvent<T> {

    private T data;
    private EventType type;

    public DeleteEvent() {
    }

}
