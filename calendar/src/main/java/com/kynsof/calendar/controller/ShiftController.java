package com.kynsof.calendar.controller;

import com.kynsof.calendar.application.command.shift.next.NextShiftRequest;
import com.kynsof.calendar.application.command.shift.next.NextShiftRequestCommand;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> stop(@RequestBody NextShiftRequest request) {
        var command = NextShiftRequestCommand.fromRequest(request);
        mediator.send(command);
        return ResponseEntity.ok(true);
    }
}