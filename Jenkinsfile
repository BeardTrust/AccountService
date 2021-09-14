pipeline {
  agent any
 
  tools {
  maven 'Maven3'
  }
  stages {
    stage ('Build') {
      steps {
      sh 'mvn clean install -f MyWebApp/pom.xml'
      }
    }
    stage ('Code Quality') {
      steps {
        withSonarQubeEnv('Code Checker') {
        sh 'mvn -f MyWebApp/pom.xml sonar:sonar'
        }
      }
    }
  }
}