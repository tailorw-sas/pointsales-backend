package com.kynsof.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Un controlador con mapeo incorrecto o conflicto en las anotaciones
@Controller
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("/test")
    public String testMethod() {
        return "test";
    }

    // Supongamos que hay otro método o controlador que intenta usar el mismo mapeo "/test"
    // Esto podría causar un conflicto
}

