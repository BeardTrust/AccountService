pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "${maven}/bin/mvn sonar:sonar"
    }
  }
  }
}