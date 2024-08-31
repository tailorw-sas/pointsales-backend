package com.kynsof.calendar.infrastructure.jobs;

import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.service.IScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class CleanScheduleWithSameStockScheduledTask {

    private final IScheduleService scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(CleanScheduleWithSameStockScheduledTask.class);

    public CleanScheduleWithSameStockScheduledTask(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void clean(){
        List<ScheduleDto> scheduleDtoList = this.scheduleService.findSchedulesWithEqualStock(LocalDate.now());
        logger.info("Limpiando los Schedule de hoy "+ LocalDate.now() +" con initialStock = stock...");
        logger.info("Existen " + scheduleDtoList.size() + " Schedule a eliminar.");
        for(ScheduleDto schedule : scheduleDtoList){
            logger.info("Eliminando Schedule con id: "+schedule.getId().toString());
            this.scheduleService.delete(schedule.getId());
        }
    }

}
