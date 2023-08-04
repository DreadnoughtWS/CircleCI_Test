pipeline {
    agent any
    stages {

      stage('test'){
        steps{
          dir(env.WORKSPACE){
            echo "${env.WORKSPACE}"
          }
            sh './gradlew clean'
        }
      }

//         stage('Clean Gradle Cache') {
//             steps {
//                 script {
//                   dir(env.LOCATION_PROJECT) {
//                     bat "fastlane runClean"
//                   }
//                 }
//             }
//         }
//
//         stage('Unit Tests') {
//             steps {
//                 dir(env.LOCATION_PROJECT) {
//                     bat 'gem -v'
//                     bat "fastlane runUnitTest"
//                 }
//             }
//         }
//
//         stage('Compile & Build APK') {
//             steps {
//                 dir(env.LOCATION_PROJECT) {
//                     bat 'java -version'
//                     bat 'fastlane runBuildApk'
//                 }
//             }
//         }
//
//         stage('Upload to Firebase') {
//             steps {
//                 dir(env.LOCATION_PROJECT) {
//                     bat 'fastlane distribute'
//                 }
//             }
//         }

    }
}