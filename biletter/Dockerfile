# ===== Build Stage =====
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY . .

# Package this module with Spring Boot repackage
RUN mvn -B -f pom.xml -pl biletter -am clean package -DskipTests

# ===== Run Stage =====
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the fat jar built by Spring Boot
COPY --from=builder /app/biletter/target/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
