package com.kynsof.calendar.application.command.schedule.update;

import com.kynsof.calendar.application.command.schedule.create.CreateScheduleCommand;
import com.kynsof.calendar.domain.dto.ReceiptDto;
import com.kynsof.calendar.domain.service.IReceiptService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleCommandHandler implements ICommandHandler<UpdateScheduleCommand> {

    private final IReceiptService serviceReceipt;
    private IMediator mediator;

    public UpdateScheduleCommandHandler(IReceiptService serviceReceipt) {
        this.serviceReceipt = serviceReceipt;
    }

    @Override
    public void handle(UpdateScheduleCommand command) {
        this.mediator = command.getMediator();
        if (command.getUser() != null) {
            /**
             * Se busca si existe una reserva para el usuario y con el mismo
             * horario.
             */
            ReceiptDto _receipt = this.serviceReceipt.findReceiptByUserIdAndScheduleId(command.getUser(), command.getId());

            if (_receipt == null) {
                /**
                 * Si no existe la reserva se manda a crear.
                 */
                this.mediator.send(new CreateScheduleCommand(
                        command.getResource(),
                        command.getBusiness(),
                        command.getDate(),
                        command.getStartTime(),
                        command.getEndingTime(),
                        command.getStock(),
                        command.getService(),
                        command.getUser(),
                        command.getIpAddress(),
                        command.getUserAgent(),
                        mediator
                ));
            }
        }

    }
}
