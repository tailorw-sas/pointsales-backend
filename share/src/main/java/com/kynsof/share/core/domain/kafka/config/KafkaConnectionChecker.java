package com.kynsof.share.core.domain.kafka.config;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaConnectionChecker {

    @Value("${KAFKA_BOOTSTRAP_ADDRESS}")
    private String bootstrapAddress;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @PostConstruct
    public void checkKafkaConnection() {
        checkAdminConnection();
        sendTestMessage();
    }

    private void checkAdminConnection() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        try (AdminClient client = AdminClient.create(configProps)) {
            client.listTopics().names().get(); // Intenta listar los temas
            System.out.println("Conexión a Kafka exitosa (AdminClient).");
        } catch (Exception e) {
            System.err.println("Error al conectar con Kafka (AdminClient): " + e.getMessage());
        }
    }

    private void sendTestMessage() {
        try {
            kafkaTemplate.send("test-topic", "Mensaje de prueba").get();
            System.out.println("Mensaje de prueba enviado a Kafka exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al enviar mensaje de prueba a Kafka: " + e.getMessage());
        }
    }
}