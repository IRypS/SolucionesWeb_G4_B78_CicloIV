FROM openjdk:18-jdk-slim

ARG DATABASE_URL=jdbc:mysql://root:ADvdTxVSjDISVyZtFStD@containers-us-west-48.railway.app:7181/cinema
ARG DATABASE_USERNAME=root
ARG DATABASE_PASSWORD=ADvdTxVSjDISVyZtFStD

COPY target/appGrupo4-*.jar movie-webpage.jar

EXPOSE 3306

ENTRYPOINT ["java", "-jar", "movie-webpage.jar"]