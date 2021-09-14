node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    agent any
            steps {
              withSonarQubeEnv('My SonarQube Server') {
                sh 'mvn clean package sonar:sonar'
              }
  }
}