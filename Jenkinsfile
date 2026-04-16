pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK1.8.0_45'
    }

    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn -B clean verify'
            }
        }
    }
}