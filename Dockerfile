FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY src/main/resources/model/model.zip /app/resources/model/model.zip
ENTRYPOINT ["java", "-jar", "app.jar"]
