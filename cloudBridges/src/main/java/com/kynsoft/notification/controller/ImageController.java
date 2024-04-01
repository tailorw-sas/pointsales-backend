package com.kynsoft.notification.controller;


import com.kynsoft.notification.infrastructure.ImageDownloadService;
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
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

    @GetMapping("/get-image-base64")
    public ResponseEntity<String> getImageAsBase64(@RequestParam("id") UUID id) {
        try {
            String imageBase64 = imageDownloadService.downloadImageAsBase64(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(imageBase64);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la imagen: " + e.getMessage());
        }
    }
}
