package com.kynsoft.socket.messages;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewServiceMessage{
   private String shift;
   private String service;
   private String local;
   private String blockCode;
}
