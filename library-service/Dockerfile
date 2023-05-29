FROM openjdk:17-alpine


EXPOSE 8081

ADD target/com.cc-0.0.1-SNAPSHOT.jar library-service.jar

ENTRYPOINT ["java","-jar","library-service.jar"]