# Set base image to OpenJDK 17
FROM openjdk:17-slim

# Set the working directory to /app
WORKDIR /app

# Copy the source files from the host machine to the container's working directory
COPY . .

# Build the application with Gradle
RUN ./gradlew clean build assemble -x test 

# Set the entry point for the container to the main class of the Spring Boot application
COPY /build/libs/products-service-0.0.1-SNAPSHOT.jar products-service.jar

EXPOSE 9082:9082

ENTRYPOINT ["java", "-jar", "products-service.jar"]