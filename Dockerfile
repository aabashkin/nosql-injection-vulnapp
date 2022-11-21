FROM eclipse-temurin:17-jdk-focal

COPY target/nosql-injection-vulnapp-mongodb-java-*.jar /app/niva.jar

WORKDIR /app

ENTRYPOINT [ "java","-jar", "niva.jar"]