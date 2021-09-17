
node {
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
//     sh "${mvn}/bin/mvn clean package -Dmaven.test.skip=true"
//     def customImage = docker.build("accountservice:Dockerfile")
   sh "docker build -t accountservice:image:v_$BUILD_NUMBER --pull=true /var/lib/jenkins/workspace/TestLine"
 sh "docker tag accountservice:image:v_$BUILD_NUMBER 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:v_$BUILD_NUMBER"
 sh "aws ecr get-login-password --region us-east-2 --profile=default | docker login --username AWS --password-stdin 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service"
 sh "docker push 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:v_$BUILD_NUMBER"
  }
//   stage('Push docker image') {
//     sh "docker push 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:v_$BUILD_NUMBER"    
//    }
}
