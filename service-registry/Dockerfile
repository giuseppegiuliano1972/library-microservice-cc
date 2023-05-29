FROM openjdk:17-alpine
EXPOSE 1111
ADD /target/service-registry-0.0.1-SNAPSHOT.jar app.jar
RUN /bin/sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
