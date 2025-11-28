# Stage 1: Build the application
# Use a Maven image to build the Spring Boot JAR file.
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download all dependencies. This is done as a separate step to leverage Docker's layer caching.
RUN mvn dependency:go-offline

# Copy the rest of the application's source code
COPY src ./src

# Package the application into an executable JAR
RUN mvn package -DskipTests


# Stage 2: Create the final, lightweight image
# Use a minimal Java runtime image
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Argument to define the path for the SSL certificate
ARG DB_CERT_PATH=/app/ca.pem

# Copy the executable JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
# It will first check for a DB_SSL_CERT environment variable and write it to the path specified by DB_CERT_PATH.
# Then, it starts the Java application.
ENTRYPOINT ["sh", "-c", "echo \"$DB_SSL_CERT\" > ${DB_CERT_PATH} && java -jar app.jar"]
