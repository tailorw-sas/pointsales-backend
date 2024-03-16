package com.kynsoft.notification.controller;


import com.kynsoft.notification.application.ImageDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageDownloadService imageDownloadService;

    @Autowired
    public ImageController(ImageDownloadService imageDownloadService) {
        this.imageDownloadService = imageDownloadService;
    }

    @GetMapping("/get-image")
    public ResponseEntity<byte[]> getImage(@RequestParam("id") UUID id) {
        byte[] imageBytes = imageDownloadService.downloadImage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
