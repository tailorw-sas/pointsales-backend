package com.kynsof.share.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration()
public class ShareConfigurationConadisRequest {

    @Bean
    public Request.Options feignBuilderConadis() {
        return new Request.Options(60000, TimeUnit.MILLISECONDS, 60000,
                TimeUnit.MILLISECONDS, false);
    }
}
