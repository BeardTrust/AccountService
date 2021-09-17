node {
  stage('SCM') {
    checkout scm
  }
   stage('SonarQube Analysis') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn sonar:sonar"
    }
   }
  stage('Build') {
    def customImage = docker.build("TestLine:Dockerfile")
    sh "docker build ${scm}/Dockerfile"
  }
//   stage('Build docker image') {
//       sh "${mvn}/bin/mvn docker build accountserviceImage/testnode"    
//    }
}
