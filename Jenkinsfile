
node {
  def customImage
  stages {
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
    customImage = docker.build("427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service")
  }
  stage('Push docker image') {
    steps {
      withDockerRegistry([url: "docker push 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service",credentialsId: {ECR_token}]) {
       sh 'docker push accountservice:latest' 
      }
      }
     }
  }
}
