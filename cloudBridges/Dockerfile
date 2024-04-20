# Usamos una imagen base oficial de OpenJDK para asegurar que tenemos una JVM compatible con nuestra aplicación Spring Boot
FROM openjdk:17-slim as builder

# Opcional: Argumento para especificar la versión del JAR
ARG JAR_VERSION=1.0.0

# Directorio de trabajo dentro del contenedor
WORKDIR /application

# Primero, un paso separado para descargar todas las dependencias necesarias
# Esto es útil para aprovechar la caché de Docker si los POMs no cambian
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw install -DskipTests

# Extraemos las capas del JAR para permitir una mejor utilización de la caché de Docker
RUN java -Djarmode=layertools -jar target/*.jar extract

# Segunda etapa del build que no tiene Maven y sólo tiene las dependencias necesarias
# Utilizamos la misma imagen base por simplicidad
FROM openjdk:17-slim

WORKDIR /application

# Copiamos las capas extraídas desde la etapa de builder
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

# El puerto que expone nuestra aplicación, esto debe coincidir con el puerto que usa Spring Boot
EXPOSE 9908

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
