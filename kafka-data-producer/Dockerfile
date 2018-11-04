FROM openjdk:8

ENV app_version 0.0.1-SNAPSHOT

COPY build/libs/kafka-data-producer-$app_version.jar app.jar

CMD ["java", "-jar", "app.jar"]
