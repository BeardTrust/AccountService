pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage('SonarQube Analysis') {
      steps{
    withSonarQubeEnv() {
      sh "${maven}/bin/mvn sonar:sonar"
    }
      }
  }
  }
}