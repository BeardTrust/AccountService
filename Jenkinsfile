pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage('SonarQube Analysis') {
      steps{
    def mvn = tool 'Default Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn sonar:sonar"
    }
      }
  }
  }
}