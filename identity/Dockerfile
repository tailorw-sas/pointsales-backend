# Build Stage: Usando una imagen ligera con Maven y JDK 17
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder

# Set the working directory
WORKDIR /build

# Copiar el código fuente y resolver dependencias en un solo paso
COPY . .

# Construir la aplicación, excluyendo tests
RUN mvn clean package -DskipTests --no-transfer-progress

# Runtime Stage: Usando una imagen ligera para ejecutar la aplicación
FROM eclipse-temurin:17-jre-alpine

# Exponer el puerto de la aplicación
EXPOSE 9909

# Configurar zona horaria
ENV TZ="America/Guayaquil"
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/${TZ} /etc/localtime && \
    echo "${TZ}" > /etc/timezone && \
    apk del tzdata

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el jar generado desde la etapa de construcción
COPY --from=builder /build/target/identity-1.0.0.jar .

# Configurar el punto de entrada
ENTRYPOINT ["java", "-jar", "/app/identity-1.0.0.jar"]