pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage('SonarQube Analysis') {
      steps{
    withSonarQubeEnv('Code Checker') {
      sh "src/bin/mvn sonar:sonar"
    }
      }
  }
  }
}