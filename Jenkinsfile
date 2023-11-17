pipeline {
    agent any
    
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    
                    // Cloner le dépôt principal sans authentification car dépot local
                    git 'https://github.com/Hamzamo99/covid_API_App.git'
    
                    // Basculer vers la branche distante Mise_en_prod
                    sh 'git checkout Mise_en_prod'
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {

                    // On éxécute docker-compose up avec un chemin relatif
                    sh 'docker-compose -f docker-compose.yaml up -d'

                }
            }
        }
    }
}