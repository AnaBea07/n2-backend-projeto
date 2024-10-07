FROM openjdk:23-jdk-oracle
EXPOSE 8080
WORKDIR /app
COPY pom.xml .
CMD ["./mvnw", "spring-boot:run"]
