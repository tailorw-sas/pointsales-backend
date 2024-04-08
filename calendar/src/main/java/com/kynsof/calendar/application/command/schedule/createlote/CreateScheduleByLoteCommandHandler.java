package com.kynsof.calendar.application.command.schedule.createlote;

import com.kynsof.calendar.application.command.schedule.createall.CreateScheduleAllCommand;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleByLoteCommandHandler implements ICommandHandler<CreateScheduleByLoteCommand> {

    private IMediator mediator;

    public CreateScheduleByLoteCommandHandler() {
    }

    @Override
    public void handle(CreateScheduleByLoteCommand command) {
        this.mediator = command.getMediator();
        List<LocalDate> dates = this.getBusinessDays(command.getStartDate(), command.getEndDate());
        for (LocalDate date : dates) {
            this.mediator.send(new CreateScheduleAllCommand(
                    command.getIdResource(),
                    command.getIdBusiness(),
                    command.getServiceId(),
                    date,
                    command.getSchedules(),
                    mediator
            ));
        }
    }

    private List<LocalDate> getBusinessDays(LocalDate startDate, LocalDate endDate) {
        // Ajustar el endDate si es sábado o domingo
        if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            endDate = endDate.minusDays(1);
        } else if (endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            endDate = endDate.minusDays(2);
        }

        List<LocalDate> businessDays = startDate.datesUntil(endDate)
                .filter(d -> !d.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                .filter(d -> !d.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .collect(Collectors.toList());

        // Verificar si el último día de la lista es igual al endDate ajustado
        if (!businessDays.isEmpty() && !businessDays.get(businessDays.size() - 1).equals(endDate)) {
            // Si no es igual, agregar el endDate ajustado a la lista
            businessDays.add(endDate);
        }

        return businessDays;
    }
}
