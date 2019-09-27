pipeline {
  agent {
    docker {
      image 'maven:3.6.2-jdk-12'
    }

  }
  stages {
    stage('Test') {
      steps {
        sh '''echo \'Unit Testing Account Service\'
'''
        dir(path: 'accounts') {
          sh 'mvn clean test'
        }

      }
    }
    stage('Package') {
      steps {
        sh '''echo \'Build and Package Account Service\'
'''
        dir(path: 'accounts') {
          sh 'mvn package'
        }

      }
    }
    stage('Deliver') {
      steps {
        sh '''echo \'Create Docker Image of Account Service\'
'''
        dir(path: 'accounts') {
          script {
            dockerImage = docker.build registry
          }

        }

      }
    }
  }
  environment {
    registry = 'wpnpeiris/american-orange-cadillac-accounts'
  }
}

