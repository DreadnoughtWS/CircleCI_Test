pipeline {
    agent any

    environment {
      ANDROID_HOME = 'C:\\Users\\davis\\AppData\\Local\\Android\\Sdk'
      LOCATION_PROJECT = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\JenkinImpact'
    }
    stages {
        stage('Clean Gradle Cache') {
            steps {
                script {
                  dir(env.LOCATION_PROJECT) {
                    bat "bundle exec fastlane runClean"
                  }
                }
            }
        }
        stage('android tests') {
            parallel {
                stage('Unit Tests') {
                     steps {
                         dir(env.LOCATION_PROJECT) {
                            bat 'gem -v'
                            bat "bundle exec fastlane runUnitTest"
                         }
                     }
                }
                stage('UI Tests') {
                    steps {
                        dir(env.LOCATION_PROJECT) {
                            bat 'bundle exec fastlane runInstrumentedTest'
                        }
                    }
                }
            }
        }

        stage('Compile & Build APK') {
            steps {
                dir(env.LOCATION_PROJECT) {
                    bat 'java -version'
                    bat 'bundle exec fastlane runBuildApk'
                }
            }
        }

//         stage('Compile & Build APK') {
//             steps {
//                 dir(env.LOCATION_PROJECT) {
//                     bat 'fastlane distribute'
//                 }
//             }
//         }

    }
}