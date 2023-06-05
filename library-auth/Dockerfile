FROM openjdk:17-alpine

VOLUME /tmp

ADD /target/library-auth-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084

RUN /bin/sh  -c 'touch /app.jar'

ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://library-auth-service/mylibauth","-Dspring.datasource.username=postgres","-Dspring.datasource.password=postgres","-jar","/app.jar"]
