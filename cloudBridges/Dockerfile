# Deployment Image
FROM bellsoft/liberica-openjdk-alpine:17.0.6

EXPOSE 9908

# Install necessary packages for runtime
RUN apk --no-cache upgrade && \
    apk add --no-cache tzdata fontconfig ttf-dejavu && \
    fc-cache -f

# Configure timezone
ENV TZ="America/Guayaquil"

# Set up Java options to run in headless mode
ENV JAVA_OPTS="-Djava.awt.headless=true"

# Copy the native executable into the container
WORKDIR /app
COPY --from=builder /build/target/cloudBridges-1.0.0.jar .

ENTRYPOINT [ "java", "-jar", "/app/cloudBridges-1.0.0.jar" ]
