pipeline {
  agent any
 
  tools {
  maven 'Maven'
  }
  stages {
    stage ('Build') {
      steps {
      sh 'mvn clean install'
      }
    }
    stage ('Code Quality') {
      steps {
        withSonarQubeEnv('Code Checker') {
        sh 'mvn -f sonar:sonar'
        }
      }
    }
  }
}