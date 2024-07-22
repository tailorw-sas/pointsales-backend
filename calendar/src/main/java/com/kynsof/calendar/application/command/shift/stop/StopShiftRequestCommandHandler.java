package com.kynsof.calendar.application.command.shift.stop;

import com.kynsof.calendar.domain.dto.AttendanceLogDto;
import com.kynsof.calendar.domain.dto.enumType.AttentionLocalStatus;
import com.kynsof.calendar.domain.service.IAttendanceLogService;
import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StopShiftRequestCommandHandler implements ICommandHandler<StopShiftRequestCommand> {

    private final IPlaceService placeService;
    private final IServiceService serviceService;
    private final IResourceService resourceService;
    private final IAttendanceLogService attendanceLogService;


    public StopShiftRequestCommandHandler( IPlaceService placeService, IServiceService serviceService,
                                           IResourceService resourceService,
                                           IAttendanceLogService attendanceLogService) {
        this.placeService = placeService;
        this.serviceService = serviceService;
        this.resourceService = resourceService;
        this.attendanceLogService = attendanceLogService;
    }

    @Override
    public void handle(StopShiftRequestCommand command) {
        var place = placeService.findById(UUID.fromString(command.getLocal()));
        var service = serviceService.findByIds(UUID.fromString(command.getService()));
        var resource = resourceService.findById(UUID.fromString(command.getDoctor()));

        AttendanceLogDto attendanceLogDto = new AttendanceLogDto();
        attendanceLogDto.setId(UUID.randomUUID());
        attendanceLogDto.setBusiness(place.getBusinessDto());
        attendanceLogDto.setResource(resource);
        attendanceLogDto.setService(service);
        attendanceLogDto.setStatus(AttentionLocalStatus.PAUSED);
        attendanceLogDto.setPlace(place);
        attendanceLogService.create(attendanceLogDto);

    }
}
