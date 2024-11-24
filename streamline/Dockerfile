FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the generated JAR file into the container
COPY target/streamline.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]