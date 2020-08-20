pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               build 'GIT HUB JOB'
            }
        }
        post {
                always {
                    junit '**/target/*.xml'
                }
                failure {
                    mail to: alexandraryzvanovich@gmail.com, subject: 'The Build failed :('
                }
            }
        stage('Test') {
            steps {
                build 'tests'

            }
        }
        post {
                always {
                    junit '**/target/*.xml'
                }
                failure {
                    mail to: alexandraryzvanovich@gmail.com, subject: 'The Test failed :('
                }
            }
        stage('Sonar check') {
                    steps {
                        build 'Sonar Scanner'
                    }
                }
                post {
                        always {
                            junit '**/target/*.xml'
                        }
                        failure {
                            mail to: alexandraryzvanovich@gmail.com, subject: 'The Sonar check failed :('
                        }
                    }
        stage('Final build') {
            steps {
                build 'build'
            }
        }
        post {
                always {
                    junit '**/target/*.xml'
                }
                failure {
                    mail to: alexandraryzvanovich@gmail.com, subject: 'The Final build failed :('
                }
            }
    }
}