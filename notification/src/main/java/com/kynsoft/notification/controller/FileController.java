package com.kynsoft.notification.controller;

import com.kynsoft.notification.application.CustomMultipartFile;
import com.kynsoft.notification.application.FileRequest;
import com.kynsoft.notification.domain.dto.AFile;
import com.kynsoft.notification.infrastructure.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final AmazonClient amazonClient;

    @Autowired
    public FileController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestBody FileRequest request) {
        try {
            MultipartFile file = new CustomMultipartFile(request.getFile(), request.getFileName());
            String fileUrl = amazonClient.save(file, request.getFileName());
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam("url") String fileUrl) {
        try {
            amazonClient.delete(fileUrl);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete file: " + e.getMessage());
        }
    }

    @GetMapping("/load")
    public ResponseEntity<AFile> loadFile(@RequestParam("url") String fileUrl) {
        try {
            AFile file = amazonClient.loadFile(fileUrl);
            return ResponseEntity.ok(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
