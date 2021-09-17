node {
  stage('SCM') {
    checkout scm
  }
   stage('SonarQube Analysis') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn sonar:sonar"
    }
  stage('Build') {
    def mvn = tool 'Maven'
      sh "${mvn}/bin/mvn clean"
      sh "${mvn}/bin/mvn install -Dmaven.test.skip=true"
      sh "docker build target/Dockerfile"
  }
//   stage('Build docker image') {
//       sh "${mvn}/bin/mvn docker build accountserviceImage/testnode"    
//    }
}
