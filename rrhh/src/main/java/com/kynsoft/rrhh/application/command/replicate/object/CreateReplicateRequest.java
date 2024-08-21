package com.kynsoft.rrhh.application.command.replicate.object;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReplicateRequest {
    private List<ObjectEnum> objects;
}
