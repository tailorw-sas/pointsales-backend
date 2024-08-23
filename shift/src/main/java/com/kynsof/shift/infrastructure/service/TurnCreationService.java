package com.kynsof.shift.infrastructure.service;

import com.kynsof.shift.application.command.turn.create.CreateTurnRequest;
import com.kynsof.shift.domain.dto.*;
import com.kynsof.shift.domain.dto.enumType.ETurnStatus;
import com.kynsof.shift.domain.service.*;
import com.kynsof.shift.infrastructure.service.socket.LocalServiceMessage;
import com.kynsof.shift.infrastructure.service.socket.NewServiceMessage;
import com.kynsof.shift.infrastructure.service.socket.NotificationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TurnCreationService {

    private final ITurnService turnService;
    private final IBusinessService businessService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;
    private final IAttendanceLogService attendanceLogService;
    private final NotificationService notificationService;

    public TurnCreationService(ITurnService turnService, IBusinessService businessService,
                               IResourceService resourceService, IServiceService serviceService,
                               IAttendanceLogService attendanceLogService,
                               NotificationService notificationService) {
        this.turnService = turnService;
        this.businessService = businessService;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
        this.attendanceLogService = attendanceLogService;
        this.notificationService = notificationService;
    }

    public UUID createTurn(CreateTurnRequest command) {
        BusinessDto businessDto = businessService.findById(command.getBusiness());

        ResourceDto resourceDto = command.getDoctor() != null ? resourceService.findById(command.getDoctor()) : null;
        ServiceDto service = serviceService.findByIds(command.getService());
        UUID serviceId = service.getId();

        AttendanceLogDto attendanceLogDto = attendanceLogService.getByServiceId(serviceId, businessDto.getId());

        if (attendanceLogDto != null) {

            var existLocalActive = this.attendanceLogService.getByLocalId(attendanceLogDto.getPlace().getId(), businessDto.getId());
            if (!existLocalActive.isEmpty()) {
                this.attendanceLogService.deleteByIds(existLocalActive.stream().map(AttendanceLogDto::getId).toList());
            }
           // attendanceLogService.delete(attendanceLogDto.getId());
            TurnDto turnDto = createTurnDto(command, resourceDto, service, businessDto, ETurnStatus.IN_PROGRESS);
            turnDto.setLocal(attendanceLogDto.getPlace().getCode());
            UUID id = turnService.create(turnDto);

            sendNotifications(service, attendanceLogDto, turnDto);

            return id;
        }

        TurnDto turnDtoCola = createTurnDto(command, resourceDto, service, businessDto, ETurnStatus.PENDING);
        UUID id = turnService.create(turnDtoCola);
        return id;
    }

    private TurnDto createTurnDto(CreateTurnRequest command, ResourceDto resourceDto, ServiceDto service, BusinessDto businessDto,
                                  ETurnStatus status) {

        int orderNumber = this.turnService.findMaxOrderNumberByServiceId(service.getId(), businessDto.getId());
        return new TurnDto(
                UUID.randomUUID(),
                resourceDto,
                service,
                command.getIdentification(),
               orderNumber + 1,
                "0 min",
                status,
                businessDto
        );
    }

    private void sendNotifications(ServiceDto service, AttendanceLogDto attendanceLogDto, TurnDto turnDto) {

        NewServiceMessage message = new NewServiceMessage();
        message.setShift(service.getCode() + "-" + String.format("%02d", turnDto.getOrderNumber()));
        message.setService(service.getName());
        message.setLocal(attendanceLogDto.getPlace().getName());
        message.setQueueId(attendanceLogDto.getPlace().getBlock().getCode());

        LocalServiceMessage localMessage = new LocalServiceMessage();
        localMessage.setService(service.getName());
        localMessage.setServiceId(service.getId().toString());
        localMessage.setQueueId(attendanceLogDto.getPlace().getId().toString());
        localMessage.setShift(message.getShift());
        localMessage.setIdentification(turnDto.getIdentification());
        localMessage.setShiftId(turnDto.getId().toString());
        localMessage.setStatus(turnDto.getStatus().toString());

        notificationService.sendNotification(message, "/api/notification/turnero");
        notificationService.sendNotification(localMessage, "/api/notification/local");
    }
}
