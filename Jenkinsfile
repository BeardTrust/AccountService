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
    def customImage = docker.build("TestLine:${env.BUILD_ID}",
                               "-f ${DOCKER_FILES_DIR}/${dockerfile} ${DOCKER_FILES_DIR}")
    sh "docker build ${scm}/Dockerfile"
  }
//   stage('Build docker image') {
//       sh "${mvn}/bin/mvn docker build accountserviceImage/testnode"    
//    }
}
