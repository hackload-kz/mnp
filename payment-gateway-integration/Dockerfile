# ===== Build Stage =====
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY . .

# Build only this module (with dependencies)
RUN mvn -B -f pom.xml -pl payment-gateway-integration -am clean package -DskipTests

# ===== Run Stage =====
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/payment-gateway-integration/target/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
