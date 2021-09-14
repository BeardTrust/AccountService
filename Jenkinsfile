node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    withSonarQubeEnv('SonarQube 7.6') {
      sh "${mvn}/bin/mvn sonar:sonar"
    }
  }
}