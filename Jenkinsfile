node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "mvn sonar:sonar"
    }
  }
          stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
}
