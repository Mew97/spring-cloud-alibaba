FROM openjdk:8-jdk-alpine
COPY demo-common-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10105
ENTRYPOINT java -Dspring.cloud.nacos.discovery.ip=`hostname -i` -jar /app.jar