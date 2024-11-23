FROM openjdk:17
EXPOSE 8080
ADD target/streamline-0.0.1-SNAPSHOT.jar streamline.jar
ENTRYPOINT ["java","jar","/streamline.jar"]

