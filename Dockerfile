FROM openjdk:17
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ./streamline/target/streamline-0.0.1-SNAPSHOT.jar streamline.jar
ENTRYPOINT ["java","-jar","/streamline.jar"]

