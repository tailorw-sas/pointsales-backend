package com.kynsof.calendar.application.command.schedule.createlote;

import com.kynsof.calendar.application.command.schedule.createall.CreateScheduleAllCommand;
import com.kynsof.calendar.domain.rules.scheduled.ScheduledDateMustBeNullRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleByLoteCommandHandler implements ICommandHandler<CreateScheduleByLoteCommand> {

    private IMediator mediator;

    public CreateScheduleByLoteCommandHandler() {
    }

    @Override
    public void handle(CreateScheduleByLoteCommand command) {
        this.mediator = command.getMediator();
        //List<LocalDate> dates = this.getBusinessDays(command.getStartDate(), command.getEndDate());
//        List<DayOfWeek> daysToExclude = new ArrayList<>();
//        daysToExclude.add(DayOfWeek.MONDAY);
//        daysToExclude.add(DayOfWeek.FRIDAY);
        List<LocalDate> dates = this.getBusinessDays(command.getStartDate(), command.getEndDate(), command.getDaysToExclude());
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

    private List<LocalDate> getBusinessDays(LocalDate startDate, LocalDate endDate, List<DayOfWeek> daysToExclude) {
        RulesChecker.checkRule(new ScheduledDateMustBeNullRule(startDate, "startDate", "The start date must be present."));
        RulesChecker.checkRule(new ScheduledDateMustBeNullRule(endDate, "endDate", "The end date must be present."));

        // Ajustar el endDate si es sábado o domingo
        if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            endDate = endDate.minusDays(1);
        } else if (endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            endDate = endDate.minusDays(2);
        }

        Stream<LocalDate> stream = startDate.datesUntil(endDate);

        // Aplicar filtro para excluir los días especificados
        stream = stream.filter(d -> !daysToExclude.contains(d.getDayOfWeek()));

        List<LocalDate> businessDays = stream.collect(Collectors.toList());

        // Verificar si el último día de la lista es igual al endDate ajustado
        if (!businessDays.isEmpty() && !businessDays.get(businessDays.size() - 1).equals(endDate)) {
            // Si no es igual, agregar el endDate ajustado a la lista
            businessDays.add(endDate);
        }

        return businessDays;
    }

    private List<LocalDate> getBusinessDays(LocalDate startDate, LocalDate endDate) {
        RulesChecker.checkRule(new ScheduledDateMustBeNullRule(startDate, "startDate", "The start date must be present."));
        RulesChecker.checkRule(new ScheduledDateMustBeNullRule(endDate, "endDate", "The end date must be present."));
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

    private List<LocalDate> getBusinessDays(LocalDate startDate, LocalDate endDate, boolean excludeSaturdays, boolean excludeSundays) {
        RulesChecker.checkRule(new ScheduledDateMustBeNullRule(startDate, "startDate", "The start date must be present."));
        RulesChecker.checkRule(new ScheduledDateMustBeNullRule(endDate, "endDate", "The end date must be present."));

        // Ajustar el endDate si es sábado o domingo
        if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            endDate = endDate.minusDays(1);
        } else if (endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            endDate = endDate.minusDays(2);
        }

        Stream<LocalDate> stream = startDate.datesUntil(endDate);

        // Aplicar filtros para excluir sábados y domingos si se indica
        if (excludeSaturdays) {
            stream = stream.filter(d -> !d.getDayOfWeek().equals(DayOfWeek.SATURDAY));
        }
        if (excludeSundays) {
            stream = stream.filter(d -> !d.getDayOfWeek().equals(DayOfWeek.SUNDAY));
        }

        List<LocalDate> businessDays = stream.collect(Collectors.toList());

        // Verificar si el último día de la lista es igual al endDate ajustado
        if (!businessDays.isEmpty() && !businessDays.get(businessDays.size() - 1).equals(endDate)) {
            // Si no es igual, agregar el endDate ajustado a la lista
            businessDays.add(endDate);
        }

        return businessDays;
    }

}
