FROM bellsoft/liberica-openjdk-alpine-musl:21
RUN apk add --no-cache curl tzdata

ARG JAR_FILE=target/*.jar

RUN mkdir /app

COPY ${JAR_FILE} /app/spring-boot-application.jar
EXPOSE 9090
ENV APPNAME=admin

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
