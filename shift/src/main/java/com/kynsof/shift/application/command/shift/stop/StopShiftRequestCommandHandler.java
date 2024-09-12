package com.kynsof.shift.application.command.shift.stop;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.shift.domain.dto.TurnDto;
import com.kynsof.shift.domain.dto.enumType.AttentionLocalStatus;
import com.kynsof.shift.domain.dto.enumType.ETurnStatus;
import com.kynsof.shift.domain.service.IAttendanceLogService;
import com.kynsof.shift.domain.service.IPlaceService;
import com.kynsof.shift.domain.service.ITurnService;
import org.springframework.stereotype.Component;

import java.util.List;
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
        if (existLocalActives != null) {
            existLocalActives.stream().map(existLocalActive -> {
                existLocalActive.setStatus(AttentionLocalStatus.PAUSED);
                attendanceLogService.update(existLocalActive);
                return null;
            });
        }

        List<TurnDto>  turnDtos = turnService.findByLocalId(place.getCode(), place.getBusinessDto().getId());
        if (turnDtos != null) {
            turnDtos.stream().map(turnDto -> {
                turnDto.setStatus(ETurnStatus.COMPLETED);
                turnService.update(turnDto);
                return null;
            });
        }


    }
}
