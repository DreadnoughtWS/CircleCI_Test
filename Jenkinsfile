pipeline {
    agent any

    environment {
      ANDROID_HOME = 'C:\\Users\\davis\\AppData\\Local\\Android\\Sdk'
      LOCATION_PROJECT = 'C:\\Users\\davis\\AndroidStudioProjects\\GroupProject'
      ADB = "C:\\Users\\davis\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb"
      APK_PATH = "${LOCATION_PROJECT}\\app\\build\\outputs\\apk\\androidTest\\debug"
    }
    stages {
        stage('Clean Gradle Cache') {
            steps {
                script {
                  dir(env.LOCATION_PROJECT) {
                  gradle(tasks: 'runClean')
                    //bat "fastlane runClean"
                  }
                }
            }
        }

        stage('Unit Tests') {
            steps {
                dir(env.LOCATION_PROJECT) {
                 gradle(tasks:"test")
                    //bat 'gem -v'
                    //bat "C:\\Ruby32-x64\\lib\\ruby\\gems\\3.2.0\\gems\\fastlane-2.214.0\\bin\\fastlane runUnitTest"
                }
            }
        }

        stage('UI Tests') {
            steps {
                dir(env.LOCATION_PROJECT) {
                    gradle(tasks:"assembleAndroidTest")
                    //install
                    bat env.ADB + ' install -r ' + env.APK_PATH
                    bat env.ADB + ' devices'
                    bat env.ADB + ' shell am instrument -w com.academy.alfagiftmini.test/androidx.test.runner.AndroidJUnitRunner'
                    //uninstall
               }
            }
        }

        stage('Compile & Build APK') {
            steps {
                dir(env.LOCATION_PROJECT) {
                gradle(tasks:"assembledebug")
                    //bat 'java -version'
                    //bat 'fastlane runBuildApk'
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