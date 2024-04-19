//package com.kynsoft.notification.controller;
//
//import com.kynsoft.notification.domain.service.IReportService;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/reports")
//public class ReportController {
//
//    private final IReportService reportService;
//
//    public ReportController(IReportService reportService) {
//        this.reportService = reportService;
//    }
//
//    @GetMapping(value = "/hello-world", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> getHelloWorldReport() {
////        try {
////            byte[] data = reportService.generatePdfReport();
////            return ResponseEntity.ok()
////                    .contentType(MediaType.APPLICATION_PDF)
////                    .body(data);
////        } catch (JRException e) {
////            e.printStackTrace();
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//        return null;
//    }
//
//    @GetMapping(value = "/hello-world1", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> getHelloWorldReport1() {
////        try {
////          //  byte[] data = reportService.generatePdfReport();
////            // Codificar el array de bytes del PDF a un String Base64
////            String base64Pdf = Base64.getEncoder().encodeToString(data);
////            return ResponseEntity.ok()
////                    .contentType(MediaType.TEXT_PLAIN)
////                    .body(base64Pdf);
////        } catch (JRException e) {
////            e.printStackTrace();
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//        return null;
//    }
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
//
//}
//
