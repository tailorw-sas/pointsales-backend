# Usar imagen base Temurin-17 Alpine
FROM eclipse-temurin:17-jdk-focal

COPY target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#Configurar Zona Horaria
ENV TZ="America/Guayaquil"

EXPOSE 9909