FROM openjdk:17-alpine

VOLUME /tmp

ADD /target/library-lending-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

RUN /bin/sh  -c 'touch /app.jar'

ENTRYPOINT ["java","-Dspring.datasource.url=jdbc:postgresql://docker-psql/myLibLend","-Dspring.datasource.username=postgres","-Dspring.datasource.password=postgres","-jar","/app.jar"]

#spring.datasource.url=jdbc:postgresql://192.168.1.23:5432/myLibLend
 # Refer to Maven build -> finalName
 #ARG JAR_FILE=target/com.ls-0.0.1-SNAPSHOT.jar
 
 # cd /opt/app
 #WORKDIR /opt/app
 
 # cp target/{jarfileName}.jar /opt/app/app.jar
 #COPY ${JAR_FILE} app.jar
 
 # java -jar /opt/app/app.jar
 #ENTRYPOINT ["java","-jar","app.jar"]