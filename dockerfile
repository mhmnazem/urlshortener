# Use a JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built JAR into the container
COPY target/urlshortening-*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
