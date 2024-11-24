FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the generated JAR file into the container
COPY target/*.jar streamline.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "streamline.jar"]