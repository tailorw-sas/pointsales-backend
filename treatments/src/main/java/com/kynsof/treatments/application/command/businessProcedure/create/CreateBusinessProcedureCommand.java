package com.kynsof.treatments.application.command.businessProcedure.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class CreateBusinessProcedureCommand implements ICommand {

    private boolean result;
    private UUID idBusiness;
    private Set<BusinessProcedurePriceRequest> procedurePrices;

    public CreateBusinessProcedureCommand(UUID idBusiness, Set<BusinessProcedurePriceRequest> services) {
        this.idBusiness = idBusiness;
        this.procedurePrices = Set.copyOf(services);

    }

    public static CreateBusinessProcedureCommand fromRequest(CreateBusinessProcedureRequest request) {
        return new CreateBusinessProcedureCommand(
                request.getIdBusiness(), 
                request.getProcedurePrices());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBusinessProcedureMessage(result);
    }
}
