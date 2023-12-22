
pipeline{
    agent any
    parameters{
        choice(name:'action', choices: 'create\ndelete', description: 'Choose create/destroy')
        string(name:'hub_name', description: 'Image hub to store the image', defaultValue: 'ashwiin11')

    }
    stages{
        stage('Git checkout'){
            when { expression { params.action == 'create' } }
            steps {
                echo Git checkout done
            }
        }
        stage('Docker build'){
            when { expression { params.action == 'create' } }
            environment{
                DOCKER_IMAGE=${params.hub_name}/authentication
            }
            steps{
                sh """
                docker login -u -p
                docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .
                docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER}  ${params.hub_name}/${DOCKER_IMAGE}:latest
                """
            }
        }

    }
}