FROM openjdk:17-jdk-slim

WORKDIR /application

COPY /target/demo-0.0.1-SNAPSHOT.jar app.jar

LABEL creator="manpreet"

EXPOSE 8080

ENTRYPOINT ["java", "-jar","app.jar"]