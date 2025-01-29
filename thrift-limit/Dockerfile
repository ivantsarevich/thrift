FROM eclipse-temurin:21-jre-alpine
COPY build/libs/thrift-limit-0.0.1-SNAPSHOT.jar limit.jar
ENTRYPOINT ["java","-jar","/limit.jar"]