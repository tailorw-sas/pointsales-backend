# Builder Image
FROM ghcr.io/graalvm/native-image:ol8-java17-22 AS builder

# Install necessary packages for the build
RUN microdnf update && \
    microdnf install --nodocs tar gzip ttf-dejavu && \
    microdnf clean all && \
    rm -rf /var/cache/yum

# Install Maven
ARG USER_HOME_DIR="/root"
ARG SHA=deaa39e16b2cf20f8cd7d232a1306344f04020e1f0fb28d35492606f647a60fe729cc40d3cba33e093a17aed41bd161fe1240556d0f1b80e773abd408686217e
ARG MAVEN_DOWNLOAD_URL=https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
    && curl -fsSL -o /tmp/apache-maven.tar.gz ${MAVEN_DOWNLOAD_URL} \
    && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
    && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
    && rm -f /tmp/apache-maven.tar.gz \
    && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven/bin/
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Set the working directory to /build
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Setup environment variables for Maven repository
ARG PACKAGE_TOKEN
ENV PACKAGE_TOKEN ${PACKAGE_TOKEN}

# Run Maven build
RUN mvn --no-transfer-progress clean package -DskipTests

# Deployment Image
FROM bellsoft/liberica-openjdk-alpine:17.0.6

EXPOSE 9908

# Install necessary packages for runtime
RUN apk upgrade --no-cache \
    && apk add --no-cache tzdata fontconfig libfreetype6 \
    && update-ms-fonts \
    && fc-cache -f

# Configure timezone
ENV TZ="America/Guayaquil"

# Set up Java options to run in headless mode
ENV JAVA_OPTS="-Djava.awt.headless=true"

# Copy the native executable into the container
WORKDIR /app
COPY --from=builder /build/target/cloudBridges-1.0.0.jar .

ENTRYPOINT [ "java", "-jar", "/app/cloudBridges-1.0.0.jar" ]