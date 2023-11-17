# Projet COVID-API

## Description
Le projet COVID-API est une application backend développée avec Gradle et Java. L'application utilise une base de données PostgreSQL pour stocker les informations.

## Installation rapide sans Jenkins en local 

### Prérequis
- Docker
- Docker Compose


### Instructions d'installation

1. Clonez le dépôt GitHub :
   ```bash
   git clone https://github.com/Hamzamo99/covid_API_App.git

2. Positionnez vous au nivau de la branche Mise_en_prod :
   ```bash
   git checkout Mise_en_prod

3. Lancez le build et le lancement de l'application en local :
   ```bash
   docker-compose -f docker-compose.yaml up -d

4. L'application sera accessible à l'adresse http://localhost:8080.
   

## Installation rapide avec Jenkins

### Prérequis
- Jenkins avec Docker installé dessus


### Instructions d'installation

1. Créez une nouvelle pipeline avec le code que vous trouvez dans le Jenkinsfile, situé dans la branche Mise_en_prod, dans jenkins.
   
1. Lancez la pipeline.
   

