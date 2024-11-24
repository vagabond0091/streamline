FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/streamline-0.0.1-SNAPSHOT.jar streamline-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/streamline-0.0.1-SNAPSHOT.jar"]

