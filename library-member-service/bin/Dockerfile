FROM openjdk:17-alpine

VOLUME /tmp

ADD /target/library-member-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

RUN /bin/sh -c  'touch /app.jar'

ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://mylibmember.clt6eul6gnxx.eu-north-1.rds.amazonaws.com:5432/mylibmember","-Dspring.datasource.username=postgres","-Dspring.datasource.password=postgres","-jar","/app.jar"]
