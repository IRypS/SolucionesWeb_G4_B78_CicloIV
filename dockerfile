FROM openjdk:18-jdk-slim

COPY target/appGrupo4-*.jar movie-webpage.jar

EXPOSE 3006

ENTRYPOINT ["java", "-jar", "movie-webpage.jar"]