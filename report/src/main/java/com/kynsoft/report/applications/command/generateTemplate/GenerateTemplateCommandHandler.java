package com.kynsoft.report.applications.command.generateTemplate;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.report.domain.dto.JasperReportTemplateDto;
import com.kynsoft.report.domain.services.IJasperReportTemplateService;
import com.kynsoft.report.domain.services.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenerateTemplateCommandHandler implements ICommandHandler<GenerateTemplateCommand> {

 private final IReportService reportService;
 private final IJasperReportTemplateService jasperReportTemplateService;
    private final JdbcTemplate jdbcTemplate;

    public GenerateTemplateCommandHandler(IReportService reportService, IJasperReportTemplateService jasperReportTemplateService, JdbcTemplate jdbcTemplate) {

        this.reportService = reportService;
        this.jasperReportTemplateService = jasperReportTemplateService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void handle(GenerateTemplateCommand command) {
        JasperReportTemplateDto reportTemplateDto = jasperReportTemplateService.findByTemplateCode(command.getJasperReportCode());
//        String jrxmlUrl = "http://d2cebw6tssfqem.cloudfront.net/cita_2024-04-17_11-38-05.jrxml";
//       Map<String, Object> parameters = new HashMap<>();
//        parameters.put("logo", "http://d3ksvzqyx4up5m.cloudfront.net/Ttt_2024-03-14_19-03-33.png");
//        parameters.put("cita", "111111");
//        parameters.put("nombres", "Keimer Montes Oliver");
//        parameters.put("identidad", "0961881992");
//        parameters.put("fecha", "2024-04-23");
//        parameters.put("hora", "10:40");
//        parameters.put("servicio", "GINECOLOGIA");
//        parameters.put("tipo", "CONSULTA EXTERNA");
//        parameters.put("direccion", "Calle 48");
//        parameters.put("lugar", "HOSPITAL MILITAR");
//        parameters.put("fecha_registro", "2024-04-23 10:40");
//        parameters.put("URL_QR", "http://d3ksvzqyx4up5m.cloudfront.net/Ttt_2024-03-14_19-03-33.png");
//        byte [] response = reportService.generatePdfReport(command.getParameters(),reportTemplateDto.getTemplateContentUrl(), new JREmptyDataSource());
//        command.setResult(response);

        try {
           // JasperReportTemplateDto reportTemplateDtoa = jasperReportTemplateService.findByTemplateCode(command.getJasperReportCode());
            String reportPath = "/reports/Payment.jasper"; // Asegúrate que la ruta sea correcta

            // Parámetros del reporte
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("fechayHora", "2024-04-23 10:40");
            parameters.put("idPayment", "1");

            // Generar el reporte PDF
            byte[] response = generatePdfReport(parameters, reportPath);

            // Establecer el resultado en el comando
            command.setResult(response);

        } catch (Exception e) {
            // Manejar excepciones
            e.printStackTrace();
        }
    }

    public byte[] generatePdfReport(Map<String, Object> parameters, String reportPath) throws JRException {

        InputStream jrxmlInput = getClass().getResourceAsStream(reportPath);
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInput);

        // Obtener los datos desde la base de datos
        String query = "SELECT * FROM payment"; // Ajusta tu consulta SQL según tu base de datos
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        // Convertir los datos a un JRDataSource
        JRDataSource dataSource = new JRBeanCollectionDataSource(rows);

        // Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    }

