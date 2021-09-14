pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage('SonarQube Analysis') {
      steps{
    withSonarQubeEnv('Code Checker') {
      sh "${maven}/bin/mvn sonar:sonar"
    }
      }
  }
  }
}