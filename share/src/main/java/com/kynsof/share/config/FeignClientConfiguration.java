
package com.kynsof.share.config;

import feign.Client;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Configuration
public class FeignClientConfiguration {

    
    @Bean
    public Request.Options feignBuilderConsultaNui() {
        return new Request.Options(300, TimeUnit.MILLISECONDS, 300,
                TimeUnit.MILLISECONDS, false);
    }

    @Bean
    public Client feignClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
                    }
                    @Override
                    public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
                    }
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final HostnameVerifier hostnameVerifier = (hostname, session) -> true;

            return new Client.Default(sslContext.getSocketFactory(), hostnameVerifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
