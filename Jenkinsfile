node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis with build') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "mvn build Dockerfile"
      sh "${mvn}/bin/mvn sonar:sonar"
    }
  }
//   stage('Build docker image') {
//       sh "${mvn}/bin/mvn docker build accountserviceImage/testnode"    
//    }
}
