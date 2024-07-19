package com.kynsoft.socket.messages;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalServiceMessage {
    // local id that receives the service
    private String queueId;

    private String shiftId;
    private String shift;
    private String service;

    private String identification;
    private boolean preferential;
    private String status;
}
