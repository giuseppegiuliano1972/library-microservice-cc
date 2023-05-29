FROM openjdk:17-alpine

VOLUME /tmp

ADD /target/library-lending-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

RUN /bin/sh  -c 'touch /app.jar'

ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://library-lending-db/myLibLend","-Dspring.datasource.username=postgres","-Dspring.datasource.password=postgres","-jar","/app.jar"]
