
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
    customImage = docker.build("427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service")
  }
  stage('Push docker image') {
    sh "aws ecr get-login-password --region us-east-2 --profile <secret_key> | docker login --username AWS --password-stdin 427380728300.dkr.ecr.us-east-2.amazonaws.com"
    sh "docker build -t beardtrust/account-service ."
    sh "docker tag beardtrust/account-service:latest 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:latest"
    sh "docker push 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:latest"
  }
}
