# --- Build Stage ---
# Use an official Maven image to build the application
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download all dependencies. This is done as a separate step to leverage Docker's caching.
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Package the application into a JAR file. Skip tests for a faster build.
RUN mvn package -DskipTests

# --- Runtime Stage ---
# Use a lightweight Java runtime image for the final container
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

