FROM openjdk:12
ADD target/library.jar library.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library.jar"]