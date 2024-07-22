package com.kynsof.calendar.application.command.shift.next;

import com.kynsof.calendar.application.command.turn.create.CreateTurnRequest;
import com.kynsof.calendar.domain.dto.*;
import com.kynsof.calendar.domain.dto.enumType.AttentionLocalStatus;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import com.kynsof.calendar.domain.service.*;
import com.kynsof.calendar.infrastructure.service.TurnCreationService;
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
    private final TurnCreationService turnCreationService;

    public NextShiftRequestCommandHandler(NotificationService notificationService, IPlaceService placeService, IServiceService serviceService, ITurnService turnService, IResourceService resourceService, IAttendanceLogService attendanceLogService, TurnCreationService turnCreationService) {
        this.notificationService = notificationService;
        this.placeService = placeService;
        this.serviceService = serviceService;
        this.turnService = turnService;
        this.resourceService = resourceService;
        this.attendanceLogService = attendanceLogService;
        this.turnCreationService = turnCreationService;
    }

    @Override
    public void handle(NextShiftRequestCommand command) {


        var place = placeService.findById(UUID.fromString(command.getLocal()));
        var service = serviceService.findByIds(UUID.fromString(command.getService()));
        var resource = resourceService.findById(UUID.fromString(command.getDoctor()));

        var existLocalActive = this.attendanceLogService.getByLocalId(place.getId(), place.getBusinessDto().getId());
        if(existLocalActive == null){
            AttendanceLogDto  createLocal = getAttendanceLogDto(place, resource, service, AttentionLocalStatus.AVAILABLE);
            attendanceLogService.create(createLocal);
        }else{
            existLocalActive.setStatus(AttentionLocalStatus.AVAILABLE);
            attendanceLogService.update(existLocalActive);
        }

        UUID serviceId = service.getId();
        if (command.getLastShift() != null && command.getLastShift().length() > 3) {
            generateNextShift(command);
        }
        List<TurnDto> turnDtoList = turnService.findByServiceId(serviceId, place.getBusinessDto().getId());

        var turnDto = turnDtoList.stream()
                .filter(turnDtoTem -> turnDtoTem.getStatus() == ETurnStatus.IN_PROGRESS)
                .findFirst()
                .orElse(turnDtoList.isEmpty() ? null : turnDtoList.get(0));

        if (turnDto == null) {
            AttendanceLogDto  attendanceLogDto = getAttendanceLogDto(place, resource, service, AttentionLocalStatus.AVAILABLE);
            attendanceLogService.create(attendanceLogDto);
            return;
        }

        sendNotification(service, turnDto, place, resource);
    }

    private void generateNextShift(NextShiftRequestCommand command) {
        var lastShift = turnService.findById(UUID.fromString(command.getLastShift()));
        lastShift.setStatus(ETurnStatus.COMPLETED);
        turnService.update(lastShift);
//        if (lastShift.getNextServices() != null) {
//            CreateTurnRequest request = getCreateTurnRequest(lastShift);
//            UUID uuid = turnCreationService.createTurn(request);
//            command.setId(uuid);
//        } else command.setId(null);
    }

    private static CreateTurnRequest getCreateTurnRequest(TurnDto lastShift) {
        CreateTurnRequest request = new CreateTurnRequest();
        request.setDoctor(lastShift.getDoctor().getId());
        request.setPriority(lastShift.getPriority());
        request.setService(lastShift.getNextServices());
        request.setBusiness(lastShift.getBusiness().getId());
        request.setIsPreferential(lastShift.getIsPreferential());
        request.setIsNeedPayment(lastShift.getIsNeedPayment());
        request.setIdentification(lastShift.getIdentification());
        return request;
    }

    private void sendNotification(ServiceDto service, TurnDto turnDto, PlaceDto place, ResourceDto resource) {
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

    private static AttendanceLogDto getAttendanceLogDto(PlaceDto place, ResourceDto resource, ServiceDto service,
                                                        AttentionLocalStatus status) {
        AttendanceLogDto attendanceLogDto = new AttendanceLogDto();
        attendanceLogDto.setId(UUID.randomUUID());
        attendanceLogDto.setBusiness(place.getBusinessDto());
        attendanceLogDto.setResource(resource);
        attendanceLogDto.setService(service);
        attendanceLogDto.setStatus(status);
        attendanceLogDto.setPlace(place);
        return attendanceLogDto;
    }
}
