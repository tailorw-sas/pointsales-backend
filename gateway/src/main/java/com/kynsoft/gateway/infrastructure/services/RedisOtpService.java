//package com.kynsoft.gateway.infrastructure.services;
//
//import com.kynsoft.gateway.domain.interfaces.IOtpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//@Service
//public class edisOtpService implements IOtpService {
//    private final RedisTemplate<String, String> redisTemplate;
//    private final Random random = new Random();
//    @Autowired
//    public RedisOtpService(RedisTemplate<String, String> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    @Override
//    public void saveOtpCode(String email, String otpCode) {
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(email, otpCode, 20, TimeUnit.MINUTES);
//    }
//
//    @Override
//    public String getOtpCode(String email) {
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        return valueOperations.get(email);
//    }
//
//    @Override
//    public String generateOtpCode() {
//        int otpCode = 100000 + random.nextInt(900000);
//        return String.valueOf(otpCode);
//    }
//}
