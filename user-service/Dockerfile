FROM openjdk:17
WORKDIR /app
COPY ./target/user-service-jar .
EXPOSE 8888
CMD ["java", "-jar", "user-service-jar.jar"]
