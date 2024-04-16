package com.kynsoft.notification.controller;

import com.kynsoft.notification.infrastructure.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/hello-world", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getHelloWorldReport() {
        try {
            byte[] data = reportService.generatePdfReport();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(data);
        } catch (JRException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/hello-world1", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getHelloWorldReport1() {
        try {
            byte[] data = reportService.generatePdfReport();
            // Codificar el array de bytes del PDF a un String Base64
            String base64Pdf = Base64.getEncoder().encodeToString(data);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(base64Pdf);
        } catch (JRException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

