FROM openjdk:17
EXPOSE 8080
ADD target/streamline-0.0.1-SNAPSHOT.jar streamline-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","jar","/streamline-0.0.1-SNAPSHOT.jar"]

