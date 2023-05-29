FROM openjdk:17-alpine

VOLUME /tmp

ADD /target/library-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

RUN /bin/sh -c  'touch /app.jar'

ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://library-db/mylibrary","-Dspring.datasource.username=postgres","-Dspring.datasource.password=postgres","-jar","/app.jar"]
