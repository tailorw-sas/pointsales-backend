package com.kynsoft.notification.controller;

import com.kynsof.share.core.application.FileRequest;
import com.kynsof.share.core.infrastructure.util.CustomMultipartFile;
import com.kynsoft.notification.domain.dto.AFileDto;
import com.kynsoft.notification.domain.service.IAFileService;
import com.kynsoft.notification.infrastructure.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final AmazonClient amazonClient;

    private final IAFileService fileService;

    @Autowired
    public FileController(AmazonClient amazonClient, IAFileService fileService) {
        this.amazonClient = amazonClient;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestBody FileRequest request) {
        try {
            MultipartFile file = new CustomMultipartFile(request.getFile(), request.getFileName());
            String fileUrl = amazonClient.save(file, request.getFileName());
            this.fileService.create(new AFileDto(UUID.randomUUID(), request.getFileName(), "", fileUrl));
            return ResponseEntity.ok("OK");
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
    public ResponseEntity<AFileDto> loadFile(@RequestParam("url") String fileUrl) {
        try {
            AFileDto file = amazonClient.loadFile(fileUrl);
            return ResponseEntity.ok(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/load/by/id")
    public ResponseEntity<AFileDto> loadFile(@RequestParam("id") UUID id) {

        try {
            AFileDto fileSave = this.fileService.findById(id);

            AFileDto file = amazonClient.loadFile(fileSave.getUrl());
            return ResponseEntity.ok(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
