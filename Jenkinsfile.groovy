#!/usr/bin/env groovy

pipeline {
    agent {
        node {
            label 'master'
        }
    }
    stages {
        stage('Initialization') {
            steps {
                script {
                    println "Cleanup"
                    sh "pwd"
                    sh "sh clean.sh"
                }
            }
        }
        stage('Compilation') {
            steps {
                script {
                    println "Compilation"
                    sh "sh build.sh"
                }
            }
        }
        stage('Publish') {
            steps {
                script {
                    println "publish"
                    sh "sh publish.sh"
                }
            }
        }
    }
    post {
        always {
            script {
                println "Post Always"
            }
            cleanWs()
        }
        success {
            script {
                println "Post Success"
            }
        }
        failure {
            script {
                println "Post Failure"
            }
        }
    }
}