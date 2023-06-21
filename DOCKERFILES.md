# Dockerfiles

Crear el dcoker file

* Tener un jar (./mvnw clean package -DskipTest)

Crear el archivo

```
FROM imagen

ARG argumento=valordefault

COPY ubciacion del jar

EXPOSE puertoexponer

ENTRYPOINT ["java", "-jar", "movie-webpage.jar"]
```

Crear la imagen docker build -t vidapelis .
Crear la imagen docker build -t vidapelis -f dockerfile.algo .

