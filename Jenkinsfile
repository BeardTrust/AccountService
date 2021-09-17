
node {
  def customImage
  stage('SCM') {
    checkout scm
  }
//    stage('SonarQube Analysis') {
//     def mvn = tool 'Maven';
//     withSonarQubeEnv() {
//       sh "${mvn}/bin/mvn sonar:sonar"
//     }
//    }
  stage('Build docker image') {
     def mvn = tool 'Maven';
    sh "${mvn}/bin/mvn clean package -Dmaven.test.skip=true"
    customImage = docker.build("accountservice:Dockerfile")
  }
  stage('Push docker image') {
  sh "docker push 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:${customImage}"
     }
}
