package com.kynsoft.socket.messages;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewServiceMessage{
   private String shift;
   private String service;
   private String local;
   private String queueId;
}
