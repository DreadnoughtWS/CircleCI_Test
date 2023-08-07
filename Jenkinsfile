pipeline {
    agent any

    environment {
      ANDROID_HOME = 'C:\\Users\\davis\\AppData\\Local\\Android\\Sdk'
      LOCATION_PROJECT = 'C:\\Users\\davis\\AndroidStudioProjects\\GroupProject'
      ADB = "${ANDROID_HOME}\\platform-tools\\adb"
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
        stage('android tests') {
            parallel {
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
                        gradle(tasks:androidAssembleDebug)
//                             gradle(tasks:"assembledebug")
//                             //install
//                             bat env.ADB + ' install -r ./app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk'
//                             bat env.ADB + ' install -r ./app/build/outputs/apk/debug/app-debug.apk'
//                             bat env.ADB + ' devices'
//                             bat env.ADB + ' shell am instrument -w com.academy.alfagiftmini.test/androidx.test.runner.AndroidJUnitRunner'
//                             bat env.ADB + ' uninstall com.academy.alfagiftmini'
//                             //uninstall
                            }
                        }
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