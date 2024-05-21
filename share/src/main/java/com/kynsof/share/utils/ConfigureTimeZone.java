package com.kynsof.share.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ConfigureTimeZone {

    private static final String ZONE_ID = "America/Guayaquil";
    
    public static LocalDateTime getTimeZone() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of(ZONE_ID));
        
        return localDateTime;
    }
    
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        return localDateTime;
    }

}
