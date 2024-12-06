package com.kynsoft.report.applications.command.generateReport;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.report.domain.dto.JasperReportTemplateDto;
import com.kynsoft.report.domain.services.IJasperReportTemplateService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GenerateReportCommandHandler implements ICommandHandler<GenerateReportCommand> {
    private static final Logger logger = LoggerFactory.getLogger(GenerateReportCommandHandler.class);
    private final RestTemplate restTemplate;
    private final IJasperReportTemplateService jasperReportTemplateService;
    private final ResourceLoader resourceLoader;

    public GenerateReportCommandHandler(RestTemplate restTemplate,
                                        IJasperReportTemplateService jasperReportTemplateService,
                                        ResourceLoader resourceLoader) {
        this.restTemplate = restTemplate;
        this.jasperReportTemplateService = jasperReportTemplateService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void handle(GenerateReportCommand command) {
        JasperReportTemplateDto reportTemplateDto = jasperReportTemplateService.findByTemplateCode(command.getJasperReportCode());
        try {
            byte[] response;
            if (Objects.equals(command.getReportFormatType(), "XLS")) {
                response = generateExcelReport(command.getParameters(), reportTemplateDto.getTemplateContentUrl(), reportTemplateDto);
            } else {
                response = generatePdfReport(command.getParameters(), reportTemplateDto.getTemplateContentUrl(), reportTemplateDto);
            }
            command.setResult(response);
        } catch (Exception e) {
            logger.error("Error generating report: ", e);
            throw new RuntimeException(e);
        }
    }

    public byte[] generatePdfReport(Map<String, Object> parameters, String reportPath, JasperReportTemplateDto reportTemplateDto) throws JRException, IOException {
        JasperReport jasperReport = getJasperReport(reportPath);
        logger.info("Generating PDF report with database: {}", reportTemplateDto.getDbConection().getName());

        JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, "temp/");
        parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JdbcTemplate jdbcTemplate = getJdbcTemplate(reportTemplateDto);

            // Si la consulta está definida en el DTO
            if (reportTemplateDto.getQuery() != null && !reportTemplateDto.getQuery().isEmpty()) {
                String query = replaceQueryParameters(reportTemplateDto.getQuery(), parameters);
                NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
                List<Map<String, Object>> rows = namedParameterJdbcTemplate.queryForList(query, parameters);

                // Usar JRBeanCollectionDataSource con los datos de la consulta
                JRDataSource jrDataSource = new JRBeanCollectionDataSource(rows);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            } else {
                // Si no hay consulta en el DTO, dejar que JasperReports use la consulta integrada en el JRXML
                Connection connection = jdbcTemplate.getDataSource().getConnection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            }

            return outputStream.toByteArray();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            virtualizer.cleanup();
        }
    }

    public byte[] generateExcelReport(Map<String, Object> parameters, String reportPath, JasperReportTemplateDto reportTemplateDto) throws JRException, IOException {
        JasperReport jasperReport = getJasperReport(reportPath);
        logger.info("Generating Excel report with database: {}", reportTemplateDto.getDbConection().getName());

        JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, "temp/");
        parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JdbcTemplate jdbcTemplate = getJdbcTemplate(reportTemplateDto);
            String query = reportTemplateDto.getQuery() != null ? reportTemplateDto.getQuery() : "";
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
            query = replaceQueryParameters(query, parameters);
            List<Map<String, Object>> rows = namedParameterJdbcTemplate.queryForList(query, parameters);

            JRDataSource jrDataSource = new JRBeanCollectionDataSource(rows);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);

            exporter.exportReport();
            return outputStream.toByteArray();
        } finally {
            virtualizer.cleanup();
        }
    }

    private JdbcTemplate getJdbcTemplate(JasperReportTemplateDto reportTemplateDto) {
        DataSource dataSource = createDataSource(reportTemplateDto.getDbConection().getUrl(), reportTemplateDto.getDbConection().getUsername(),
                reportTemplateDto.getDbConection().getPassword());
        return new JdbcTemplate(dataSource);
    }

    private JasperReport getJasperReport(String reportPath) throws JRException, IOException {
        try (InputStream jrxmlInput = loadReportInputStream(reportPath)) {
            logger.info("JRXML content loaded successfully from: {}", reportPath);
            return JasperCompileManager.compileReport(jrxmlInput);
        }
    }

    private InputStream loadReportInputStream(String reportPath) throws IOException {
        Resource localResource = resourceLoader.getResource("classpath:templates/" + reportPath);
        if (localResource.exists()) {
            logger.info("Loading JRXML template from local resources: templates/{}", reportPath);
            return localResource.getInputStream();
        } else {
            logger.warn("Local template not found, fetching JRXML template from URL: {}", reportPath);
            return new ByteArrayInputStream(Objects.requireNonNull(restTemplate.getForObject(reportPath, byte[].class)));
        }
    }

    private DataSource createDataSource(String url, String username, String password) {
        logger.info("Connecting to database: {}", url);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private String replaceQueryParameters(String query, Map<String, Object> parameters) {
        Pattern pattern = Pattern.compile("::([a-zA-Z]\\w*)");
        Matcher matcher = pattern.matcher(query);
        StringBuffer resultQuery = new StringBuffer();

        while (matcher.find()) {
            String paramName = matcher.group(1);
            Object value = parameters.get(paramName);

            if (value == null) {
                throw new IllegalArgumentException("Parameter " + paramName + " not found in the parameters map.");
            }
            matcher.appendReplacement(resultQuery, value.toString());
        }
        matcher.appendTail(resultQuery);

        return resultQuery.toString();
    }
}
