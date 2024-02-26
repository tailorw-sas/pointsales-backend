package com.kynsoft.email.shared.infrastructure.exceptions;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(AsyncExceptionHandler.class);
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error(
                "Unexpected asynchronus exception at: " + method.getDeclaringClass().getName() + "." + method.getName(),
                ex);
    }
}
