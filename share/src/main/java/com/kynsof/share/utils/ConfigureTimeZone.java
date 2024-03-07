package com.kynsof.share.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConfigureTimeZone {

    private static final String ZONE_ID = "America/Guayaquil";
    
    public static LocalDateTime getTimeZone() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of(ZONE_ID));
        
        return localDateTime;
    }
}
