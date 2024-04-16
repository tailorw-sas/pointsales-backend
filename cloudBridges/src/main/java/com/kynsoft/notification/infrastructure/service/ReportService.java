package com.kynsoft.notification.infrastructure.service;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final ResourceLoader resourceLoader;

    public ReportService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public byte[] generatePdfReport() throws IOException, JRException {
        Resource resource = resourceLoader.getResource("classpath:templates/hello.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(new ArrayList<>());

        Map<String, Object> parameters = new HashMap<>();
       // parameters.put("Message", "This is a dynamic message!");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}