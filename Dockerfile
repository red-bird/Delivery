FROM openjdk:17-jdk-alpine
EXPOSE 8080

COPY /build/libs/Delivery-0.0.1-SNAPSHOT.jar Delivery-0.0.1-SNAPSHOT.jar
COPY ./build/resources/main src/main/resources
ENTRYPOINT ["java", "-jar", "Delivery-0.0.1-SNAPSHOT.jar"]