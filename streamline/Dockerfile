FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the generated JAR file into the container
COPY streamline/target/streamline.jar streamline.jar

# Run the application
ENTRYPOINT ["java", "-jar", "streamline.jar"]

# Expose port 8080 (if your app uses this port)
EXPOSE 8080
