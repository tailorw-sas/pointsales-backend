package com.kynsof.shift.infrastructure.jobs;

import com.kynsof.shift.domain.dto.TurnDto;
import com.kynsof.shift.domain.service.ITurnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class CleanTurnScheduledTask {

    private final ITurnService turnService;

    private static final Logger logger = LoggerFactory.getLogger(CleanTurnScheduledTask.class);

    public CleanTurnScheduledTask(ITurnService turnService) {
        this.turnService = turnService;
    }

//    @Scheduled(fixedRateString = "20", timeUnit = TimeUnit.SECONDS)
    @Scheduled(cron = "0 0 2 * * *")
    public void clean(){
        List<TurnDto> turnDtoList = this.turnService.findByCreateAtBefore(LocalDate.now().atStartOfDay());
        logger.info("Limpiando los Turn hasta el día anterior a "+ LocalDate.now());
        logger.info("Existen " + turnDtoList.size() + " Turn a eliminar.");
        for(TurnDto turn : turnDtoList){
            logger.info("Eliminando Turn con id: "+turn.getId().toString());
            this.turnService.delete(turn.getId());
        }
    }

}
