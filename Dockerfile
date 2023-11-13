# Dockerfile pour construire l'application avec Gradle
FROM gradle:jdk17 AS builder

WORKDIR /app
COPY . /app

RUN gradle build

# Dockerfile pour le deuxième conteneur
FROM eclipse-temurin:17-jre-ubi9-minimal

RUN mkdir /opt/app

# Copie le JAR construit à partir du conteneur temporaire dans le répertoire /opt/app de l'image
COPY --from=builder /app/build/libs/covid-api-0.0.1-SNAPSHOT.jar /opt/app

CMD ["java", "-jar", "/opt/app/covid-api-0.0.1-SNAPSHOT.jar"]
