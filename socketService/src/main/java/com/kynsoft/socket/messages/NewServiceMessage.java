package com.kynsoft.socket.messages;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewServiceMessage{
    private String identification;
    private String name;
    private String zone;
    private String company;
    private String department;
    private String status;
    private String campId;
}
