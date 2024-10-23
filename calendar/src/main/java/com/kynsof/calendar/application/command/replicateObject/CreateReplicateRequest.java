package com.kynsof.calendar.application.command.replicateObject;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReplicateRequest {
    private List<ObjectEnum> objects;
}
