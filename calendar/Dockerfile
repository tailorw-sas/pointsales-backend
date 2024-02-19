FROM maven:3.8.5-openjdk-17-slim as build

COPY src /home/app/src
COPY pom.xml /home/app

WORKDIR /home/app

RUN mvn clean package

FROM eclipse-temurin:17-jdk-focal

ENV TZ="America/Guayaquil"

COPY --from=build /home/app/target/*.jar /app.jar

EXPOSE 9908

ENTRYPOINT ["java","-jar","/app.jar"]
