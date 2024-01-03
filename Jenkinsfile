pipeline {
    agent any
    parameters {
        choice(name: 'action', choices: 'create\ndelete', description: 'Choose create/destroy')
        string(name: 'hub_name', description: 'Hub to push the container image', defaultValue: 'ashwiin11')
    }
    stages {
        stage('Git checkout') {
            when {
                expression {
                    params.action == 'create'
                }
            }
            steps {
                sh 'echo Git checkout done'
            }
        }
        stage('Docker build') {
            when {
                expression {
                    params.action == 'create'
                }
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-cred', passwordVariable: 'password', usernameVariable: 'username')]) {
                        // Corrected docker login command
                        sh "docker login -u $username -p $password"
                        sh '''
                            docker build -t connect-authentication .
                            docker tag connect-authentication ${params.hub_name}/connect-authentication:$BUILD_NUMBER
                            docker push ${params.hub_name}/connect-authentication:$BUILD_NUMBER
                        '''
                        // Logout from Docker registry
                        sh 'docker logout'
                    }
                }
            }
        }
    }
}
