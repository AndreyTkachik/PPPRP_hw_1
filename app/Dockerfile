FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY /target/app-0.0.1-SNAPSHOT.jar app.jar
VOLUME /app/logs
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
