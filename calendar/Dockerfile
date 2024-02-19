# Usar imagen base Temurin-17 Alpine
FROM eclipse-temurin:17-jdk-focal

COPY target/calendar-1.0.0.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#Configurar Zona Horaria
ENV TZ="America/Guayaquil"

EXPOSE 9908