FROM openjdk:17-alpine


EXPOSE 8082

ADD target/com.ms-0.0.1-SNAPSHOT.jar library-member-service.jar

ENTRYPOINT ["java","-jar","library-member-service.jar"]