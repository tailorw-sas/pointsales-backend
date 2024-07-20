package com.kynsof.calendar.application.command.turn.create;

import com.kynsof.calendar.domain.dto.*;
import com.kynsof.calendar.domain.dto.enumType.ETurnStatus;
import com.kynsof.calendar.domain.service.*;
import com.kynsof.calendar.infrastructure.service.socket.LocalServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NewServiceMessage;
import com.kynsof.calendar.infrastructure.service.socket.NotificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateTurnCommandHandler implements ICommandHandler<CreateTurnCommand> {

    private final ITurnService turnService;
    private final IBusinessService businessService;
    private final IResourceService resourceService;
    private final IServiceService serviceService;
    private  final IAttendanceLogService attendanceLogService;
    private final NotificationService notificationService;

    public CreateTurnCommandHandler(ITurnService turnService, IBusinessService businessService,
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

    @Override
    public void handle(CreateTurnCommand command) {
        BusinessDto businessDto = businessService.findById(command.getBusiness());

        ResourceDto resourceDto = command.getDoctor() != null ? resourceService.findById(command.getDoctor()) : null;
        ServiceDto service = serviceService.findByIds(command.getService());
        AttendanceLogDto attendanceLogDto = attendanceLogService.getByServiceId(service.getId(), businessDto.getId());

        if(attendanceLogDto != null) {
            attendanceLogService.delete(attendanceLogDto.getId());
           // int position = turnService.findPositionByServiceId(service.getId(), businessDto.getId());
            var turnDto = new TurnDto(
                    command.getId(),
                    resourceDto,
                    service,
                    command.getIdentification(),
                    RandomNumberGenerator.generateRandomNumber(0,100),
                    command.getPriority(),
                    command.getIsPreferential(),
                    "0 min",
                    ETurnStatus.IN_PROGRESS,
                    businessDto,
                    0,
                    command.getIsNeedPayment()
            );
            UUID id = turnService.create(turnDto);

            var message = new NewServiceMessage();
            message.setShift(service.getCode() + "-" + String.format("%02d", 1));
            message.setService(service.getName());
            message.setLocal(attendanceLogDto.getPlace().getCode());

            var block = attendanceLogDto.getPlace().getBlock();
            message.setQueueId(block.getCode());

            // message to send to the local queue
            var localMessage = new LocalServiceMessage();
            localMessage.setService(service.getName());
            localMessage.setQueueId(attendanceLogDto.getPlace().getId().toString());
            localMessage.setShift(message.getShift());
            localMessage.setPreferential(turnDto.getIsPreferential());
            localMessage.setPreferential(turnDto.getIsPreferential());
            localMessage.setIdentification(turnDto.getIdentification());
            localMessage.setShiftId(turnDto.getId().toString());
            localMessage.setStatus(turnDto.getStatus().toString());
            notificationService.sendNotification(message, "/api/notification/turnero");
            notificationService.sendNotification(localMessage, "/api/notification/local");
            command.setId(id);
            return;
        }

      //  int position = turnService.findPositionByServiceId(service.getId(), businessDto.getId());
        UUID id = turnService.create(new TurnDto(
                command.getId(),
                resourceDto,
                service,
                command.getIdentification(),
                RandomNumberGenerator.generateRandomNumber(0,100),
                command.getPriority(),
                command.getIsPreferential(),
                "0 min",
                ETurnStatus.PENDING,
                businessDto,
                1,
                command.getIsNeedPayment()
        ));
        command.setId(id);
    }
}