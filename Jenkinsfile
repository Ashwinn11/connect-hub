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
        stage('Maven build'){
            when {
                expression{
                    params.action== 'create'
                }
            }
            steps{
                script{
                    sh 'mvn package -DskipTests'
                }
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
                            docker tag connect-authentication ashwiin11/connect-auth:1.1
                            docker push ashwiin11/connect-auth:1.1
                        '''
                        // Logout from Docker registry
                        sh 'docker logout'
                    }
                }
            }
        }
    }
}
