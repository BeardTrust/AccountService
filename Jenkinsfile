node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "mvn clean verify sonar:sonar"
    }
  }
          stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
}
