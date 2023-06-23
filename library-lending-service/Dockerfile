FROM openjdk:17-alpine

VOLUME /tmp

ADD /target/library-lending-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

RUN /bin/sh  -c 'touch /app.jar'

ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://myliblend.clt6eul6gnxx.eu-north-1.rds.amazonaws.com:5432/myliblend","-Dspring.datasource.username=postgres","-Dspring.datasource.password=postgres","-jar","/app.jar"]
