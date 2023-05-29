FROM openjdk:17-alpine


EXPOSE 8083

ADD target/com.ls-0.0.1-SNAPSHOT.jar com.ls-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","com.ls-0.0.1-SNAPSHOT.jar"]
 # Refer to Maven build -> finalName
 #ARG JAR_FILE=target/com.ls-0.0.1-SNAPSHOT.jar
 
 # cd /opt/app
 #WORKDIR /opt/app
 
 # cp target/{jarfileName}.jar /opt/app/app.jar
 #COPY ${JAR_FILE} app.jar
 
 # java -jar /opt/app/app.jar
 #ENTRYPOINT ["java","-jar","app.jar"]