node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "mvn sonar:sonar -DskipTests"
    }
  }
          stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
}
