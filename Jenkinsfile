pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage ('Build') {
      steps {
      sh 'mvn clean install -f AccountService/pom.xml'
      }
    }
    stage ('Code Quality') {
      steps {
        withSonarQubeEnv('Code Checker') {
        sh 'mvn -f AccountService/pom.xml sonar:sonar'
        }
      }
    }
  }
}