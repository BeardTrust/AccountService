node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh 'mvn clean package sonar:sonar'
    }
  }
}