pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'mvn compile'
            }
        }
    }
    
    post {
        always {
            //archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            //junit 'build/reports/**/*.xml'
        }
    }
}

