pipeline {
    agent any
 
    tools {
        maven 'mvn' // Name of Maven tool configured in Jenkins
    }
 
    environment {
        IMAGE_NAME = 'myapp'
        DOCKERHUB_USERNAME = 'your-dockerhub-username' // Optional
    }
 
    stages {
        stage('Checkout GitHub') {
            steps {
git url: 'https://github.com/your-user/your-repo.git', branch: 'main'
                echo "Checked out code and Dockerfile from GitHub."
            }
        }
 
        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
                echo "Maven build completed."
            }
        }
 
        stage('Build Docker Image') {
            steps {
                script {
docker.build("${IMAGE_NAME}:latest")
                }
                echo "Docker image built successfully."
            }
        }
    }
 
    post {
        success {
            echo '✅ Pipeline finished successfully!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}