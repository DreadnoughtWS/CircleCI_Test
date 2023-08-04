pipeline {
    agent any
    stages {



        stage('Clean Gradle Cache') {
            steps {
              sh 'gem -v'
              sh "fastlane runClean"
            }
        }
//
        stage('Unit Tests') {
            steps {
              sh "fastlane runUnitTest"

            }
        }
//
        stage('Compile & Build APK') {
            steps {
              sh 'java -version'
              sh 'fastlane runBuildApk'

            }
        }
//
        stage('Upload to Firebase') {
            steps {
              sh 'fastlane distribute'

            }
        }

    }
}