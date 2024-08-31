package com.kynsof.shift.infrastructure.jobs;

import com.kynsof.shift.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.shift.domain.service.ITurnerSpecialtiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class CleanTurnSpecialtiesScheduledTask {

    private final ITurnerSpecialtiesService turnService;

    private static final Logger logger = LoggerFactory.getLogger(CleanTurnSpecialtiesScheduledTask.class);

    public CleanTurnSpecialtiesScheduledTask(ITurnerSpecialtiesService turnService) {
        this.turnService = turnService;
    }

//    @Scheduled(fixedRateString = "20", timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron = "0 0 2 * * *")
    public void clean(){
        List<TurnerSpecialtiesDto> turnDtoList = this.turnService.findByShiftDateTimeBefore(LocalDate.now().atStartOfDay());
        logger.info("Limpiando los TurnSpecialties hasta el día anterior a "+ LocalDate.now());
        logger.info("Existen " + turnDtoList.size() + " TurnSpecialties a eliminar.");
        for(TurnerSpecialtiesDto turn : turnDtoList){
            logger.info("Eliminando TurnSpecialties con id: "+turn.getId().toString());
            this.turnService.delete(turn.getId());
        }
    }

}
