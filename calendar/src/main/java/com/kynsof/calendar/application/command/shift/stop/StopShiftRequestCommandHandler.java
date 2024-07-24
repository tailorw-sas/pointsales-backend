package com.kynsof.calendar.application.command.shift.stop;

import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.dto.enumType.AttentionLocalStatus;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import com.kynsof.calendar.domain.service.IAttendanceLogService;
import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.calendar.domain.service.ITurnService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StopShiftRequestCommandHandler implements ICommandHandler<StopShiftRequestCommand> {

    private final IPlaceService placeService;
    private final IAttendanceLogService attendanceLogService;
    private final ITurnService turnService;


    public StopShiftRequestCommandHandler(IPlaceService placeService,
                                          IAttendanceLogService attendanceLogService, ITurnService turnService) {
        this.placeService = placeService;
        this.attendanceLogService = attendanceLogService;
        this.turnService = turnService;
    }

    @Override
    public void handle(StopShiftRequestCommand command) {
        var place = placeService.findById(UUID.fromString(command.getLocal()));
        var existLocalActives = this.attendanceLogService.getByLocalId(place.getId(), place.getBusinessDto().getId());
        existLocalActives.stream().map(existLocalActive -> {
            existLocalActive.setStatus(AttentionLocalStatus.PAUSED);
            attendanceLogService.update(existLocalActive);
            return null;
        });

        TurnDto turnDto = turnService.findByLocalId(place.getCode(), place.getBusinessDto().getId());
        turnDto.setStatus(ETurnStatus.COMPLETED);
        turnService.update(turnDto);

    }
}
