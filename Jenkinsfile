pipeline {
    agent any

    environment {
      ANDROID_HOME = 'C:\\Users\\theni\\AppData\\Local\\Android\\Sdk'
      LOCATION_PROJECT = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Group Project'
      ADB = "${ANDROID_HOME}\\platform-tools\\adb"
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
          steps{
            parallel(
              unit_test: {dir(env.LOCATION_PROJECT)
              {bat 'bundle exec fastlane runUnitTest'}},
              ui_test: {dir(env.LOCATION_PROJECT)
              {bat 'bundle exec fastlane runInstrumentedTest'}}
            )
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