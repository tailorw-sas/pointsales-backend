# Usar imagen base Temurin-17 Alpine
FROM eclipse-temurin:17-jdk-focal

COPY target/*.jar /app.jar


ENTRYPOINT ["java","-jar","/app.jar"]

#Configurar Zona Horaria
ENV TZ="America/Guayaquil"

# Expone el puerto que usa tu aplicación, cambiar si es necesario
EXPOSE 9906