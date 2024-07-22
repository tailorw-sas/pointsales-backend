package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.shift.next.NextShiftRequest;
import com.kynsof.calendar.application.command.shift.next.NextShiftRequestCommand;
import com.kynsof.calendar.application.command.shift.stop.StopShiftRequest;
import com.kynsof.calendar.application.command.shift.stop.StopShiftRequestCommand;
import com.kynsof.calendar.application.command.shift.util.PriorityQueueController;
import com.kynsof.calendar.application.command.shift.util.Response;
import com.kynsof.calendar.application.command.shift.util.ResponseCode;
import com.kynsof.calendar.application.command.shift.util.Tuple;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

    private final IMediator mediator;

    public ShiftController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> next(@RequestBody NextShiftRequest request) {
        var command = NextShiftRequestCommand.fromRequest(request);
        mediator.send(command);
        return ResponseEntity.ok(true);
    }

    @PostMapping("stop")
    public ResponseEntity<?> stop(@RequestBody StopShiftRequest request) {
        var command = StopShiftRequestCommand.fromRequest(request);
        mediator.send(command);
        return ResponseEntity.ok(true);
    }

    @GetMapping()
    public ResponseEntity<?> example() {
        PriorityQueueController priorityQueueController = new PriorityQueueController();

        Response<Void> addResponse = priorityQueueController.addPriorityQueue("HIGH", "HIGH QUEUE", 5, 1);
        if (addResponse.getCode() != ResponseCode.SUCCESS) {
            System.out.println("Error: " + addResponse.getDescription());
            return null;
        }

        addResponse = priorityQueueController.addPriorityQueue("MEDIUM", "MEDIUM QUEUE", 3, 2);
        if (addResponse.getCode() != ResponseCode.SUCCESS) {
            System.out.println("Error: " + addResponse.getDescription());
            return null;
        }

        addResponse = priorityQueueController.addPriorityQueue("LOW", "LOW QUEUE", 2, 3);
        if (addResponse.getCode() != ResponseCode.SUCCESS) {
            System.out.println("Error: " + addResponse.getDescription());
            return null;
        }

        Random rnd = new Random();
        int totalShift = rnd.nextInt(96) + 5; // Random number between 5 and 100
        for (int i = 0; i < totalShift; i++) {
            int rndQueue = rnd.nextInt(3) + 1;
            switch (rndQueue) {
                case 1:
                    Response<Integer> responseHigh = priorityQueueController.queueShift("HIGH");
                    if (responseHigh.getCode() != ResponseCode.SUCCESS) {
                        System.out.println("Error: " + responseHigh.getDescription());
                        continue;
                    }
                    System.out.println("Assigned HIGH shift: " + responseHigh.getValue());
                    break;
                case 2:
                    Response<Integer> responseMedium = priorityQueueController.queueShift("MEDIUM");
                    if (responseMedium.getCode() != ResponseCode.SUCCESS) {
                        System.out.println("Error: " + responseMedium.getDescription());
                        continue;
                    }
                    System.out.println("Assigned MEDIUM shift: " + responseMedium.getValue());
                    break;
                case 3:
                    Response<Integer> responseLow = priorityQueueController.queueShift("LOW");
                    if (responseLow.getCode() != ResponseCode.SUCCESS) {
                        System.out.println("Error: " + responseLow.getDescription());
                        continue;
                    }
                    System.out.println("Assigned LOW shift: " + responseLow.getValue());
                    break;
            }
        }

        System.out.println();
        priorityQueueController.print();
        System.out.println();

        for (int i = 0; i < totalShift; i++) {
            Response<Tuple<String, Integer>> response = priorityQueueController.dequeueShift();
            if (response.getCode() != ResponseCode.SUCCESS) {
                System.out.println("Error: " + response.getDescription());
                continue;
            }

            System.out.println((i + 1) + "- Queue attended: " + response.getValue().x + ", Shift: " + response.getValue().y);
        }

        System.out.println();
        priorityQueueController.print();
        System.out.println();

        return null;
    }
}