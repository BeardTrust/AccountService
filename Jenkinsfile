
node {
  def customImage
  stage('SCM Checkout') {
    checkout scm
  }
  
   stage('SonarQube Analysis') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn sonar:sonar"
    }
   }
  stage('Build docker image') {
     def mvn = tool 'Maven';
    sh "${mvn}/bin/mvn clean package -Dmaven.test.skip=true"
    customImage = docker.build("427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service")
  }
  
  stage('Push docker image') {
    docker.withRegistry("https://427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service", 'ecr:us-east-2:nathanael_access_key'){
                        docker.image('427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service').push('latest')}
  }
  stage('Remove Unused docker image') {
    sh "docker rmi 427380728300.dkr.ecr.us-east-2.amazonaws.com/beardtrust/account-service:latest"
  
}
  

}
