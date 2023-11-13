# On utilise l'image Eclipse Temurin (AdoptOpenJDK) basée sur Java 17 (JRE) et la distribution UBI (Universal Base Image) 9 comme base
FROM eclipse-temurin:17-jre-ubi9-minimal

# On crée un répertoire /opt/app dans l'image Docker où l'application sera placée
RUN mkdir /opt/app

# On copie le fichier JAR de l'application Java construite dans le répertoire /opt/app de l'image
COPY build/libs/covid-api-0.0.1-SNAPSHOT.jar /opt/app

# Commande qui sera exécutée lors du démarrage du conteneur
CMD ["java", "-jar", "/opt/app/covid-api-0.0.1-SNAPSHOT.jar"]
