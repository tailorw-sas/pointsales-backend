package com.kynsoft.notification.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.generateTemplate.GenerateTemplateCommand;
import com.kynsoft.notification.application.command.generateTemplate.GenerateTemplateMessage;
import com.kynsoft.notification.application.command.generateTemplate.GenerateTemplateRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final IMediator medietor;

    public ReportController(IMediator medietor) {
        this.medietor = medietor;
    }

    @PostMapping(value = "/generate-template", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> getTemplate(@RequestBody GenerateTemplateRequest request) {

        GenerateTemplateCommand command = new GenerateTemplateCommand(request.getParameters(),
                request.getJasperReportCode());
        GenerateTemplateMessage response = medietor.send(command);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(response.getResult());
    }

    @GetMapping(value = "/hello-world1", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getHelloWorldReport1() {
//        try {
//          //  byte[] data = reportService.generatePdfReport();
//            // Codificar el array de bytes del PDF a un String Base64
//            String base64Pdf = Base64.getEncoder().encodeToString(data);
//            return ResponseEntity.ok()
//                    .contentType(MediaType.TEXT_PLAIN)
//                    .body(base64Pdf);
//        } catch (JRException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

//    @GetMapping("/parameters")
//    public ResponseEntity<String> getReportParameters() {
//        try {
//            String jrxmlUrl = "http://d2cebw6tssfqem.cloudfront.net/cita_2024-04-17_11-38-05.jrxml";
//            String parametersJson = reportService.getReportParameters(jrxmlUrl);
//            return ResponseEntity.ok(parametersJson);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body("Error obtaining report parameters: " + e.getMessage());
//        }
//    }

}

