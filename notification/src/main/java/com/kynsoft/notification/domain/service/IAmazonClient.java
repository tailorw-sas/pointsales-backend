package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.domain.dto.AFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface IAmazonClient {


    void uploadFile(InputStream streamToUpload, Long size, String contentType, String objectKey) throws IOException;

    String save(MultipartFile file, String folder) throws IOException;

    void delete(String url);

    AFile loadFile(String url);
}
