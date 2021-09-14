pipeline {
  agent any
 
  tools {
  mvn 'Maven'
  }
  stages {
    stage('SonarQube Analysis') {
      steps{
    withSonarQubeEnv('Code Checker') {
      sh "${mvn}/bin/mvn sonar:sonar"
    }
      }
  }
  }
}