FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 7777

ENTRYPOINT ["java", "-jar", "app.jar"]