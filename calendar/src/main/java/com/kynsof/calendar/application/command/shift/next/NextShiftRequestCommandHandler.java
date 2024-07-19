package com.kynsof.calendar.application.command.shift.next;

import com.kynsof.calendar.domain.dto.AttendanceLogDto;
import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.dto.enumType.AttentionLocalStatus;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import com.kynsof.calendar.domain.service.*;
import com.kynsof.calendar.infrastructure.service.socket.LocalServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NewServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NotificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class NextShiftRequestCommandHandler implements ICommandHandler<NextShiftRequestCommand> {

    private final NotificationService notificationService;
    private final IPlaceService placeService;
    private final IServiceService serviceService;
    private final ITurnService turnService;
    private final IResourceService resourceService;
    private final IAttendanceLogService attendanceLogService;

    public NextShiftRequestCommandHandler(NotificationService notificationService, IPlaceService placeService, IServiceService serviceService, ITurnService turnService, IResourceService resourceService, IAttendanceLogService attendanceLogService) {
        this.notificationService = notificationService;
        this.placeService = placeService;
        this.serviceService = serviceService;
        this.turnService = turnService;
        this.resourceService = resourceService;
        this.attendanceLogService = attendanceLogService;
    }

    @Override
    public void handle(NextShiftRequestCommand command) {
        var place = placeService.findById(UUID.fromString(command.getLocal()));
        var service = serviceService.findByIds(UUID.fromString(command.getService()));
        var resource = resourceService.findById(UUID.fromString(command.getDoctor()));

        if (command.getLastShift() != null && command.getLastShift().length() > 3) {
            var lastShift = turnService.findById(UUID.fromString(command.getLastShift()));
            lastShift.setStatus(ETurnStatus.COMPLETED);
            turnService.update(lastShift);
        }



        List<TurnDto> turnDtoList = turnService.findByServiceId(service.getId(), place.getBusinessDto().getId());

        var turnDto = turnDtoList.stream()
                .filter(turnDtoTem -> turnDtoTem.getStatus() == ETurnStatus.IN_PROGRESS)
                .findFirst()
                .orElse(turnDtoList.isEmpty() ? null : turnDtoList.get(0));

        if (turnDto == null) {
            AttendanceLogDto attendanceLogDto = new AttendanceLogDto();
            attendanceLogDto.setId(UUID.randomUUID());
            attendanceLogDto.setBusiness(place.getBusinessDto());
            attendanceLogDto.setResource(resource);
            attendanceLogDto.setService(service);
            attendanceLogDto.setStatus(AttentionLocalStatus.AVAILABLE);
            attendanceLogDto.setPlace(place);
            attendanceLogService.create(attendanceLogDto);
            return;
        }

        // message to send to the shift queue
        var message = new NewServiceMessage();
        message.setShift(service.getCode() + "-" + String.format("%02d", turnDto.getOrderNumber()));
        message.setService(service.getName());
        message.setLocal(place.getName());

        var block = place.getBlock();
        message.setQueueId(block.getCode());

        // message to send to the local queue
        var localMessage = new LocalServiceMessage();
        localMessage.setService(service.getName());
        localMessage.setQueueId(place.getId().toString());
        localMessage.setShift(message.getShift());
        localMessage.setPreferential(turnDto.getIsPreferential());
        localMessage.setPreferential(turnDto.getIsPreferential());
        localMessage.setIdentification(turnDto.getIdentification());
        localMessage.setShiftId(turnDto.getId().toString());
        localMessage.setStatus(turnDto.getStatus().toString());

        turnDto.setLocal(place.getCode());
        turnDto.setDoctor(resource);
        turnDto.setStatus(ETurnStatus.IN_PROGRESS);
        turnService.update(turnDto);

        // TODO: Send the notification using integration events
        notificationService.sendNotification(message, "/api/notification/turnero");
        notificationService.sendNotification(localMessage, "/api/notification/local");
    }
}
