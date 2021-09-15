node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "mvn clean verify sonar:sonar"
      sh "${mvn}/bin/mvn sonar:sonar"
    }
  }
}
