FROM azul/zulu-openjdk-alpine:21-latest

WORKDIR /opt/sensapi

COPY ../.. .

RUN ./mvnw test