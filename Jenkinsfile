pipeline {
    agent any

//     environment {
//         // Fastlane Env Configuration
//         LANG = 'en_US.UTF-8'
//         LC_ALL = 'en_US.UTF-8'
//         // // Rbenv VM Path Configuration
//         PATH = "/Users/gli-mac/.rbenv/shims:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin"
//         // Rbenv Local Path Configuration
//         // PATH = "/Users/avendisianipar/.rbenv/shims:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin"
//     }

//     stage('Configure Environment') {
//         steps {
//             // sh "echo plutil -replace ReleaseName -string '${params.BASE_URL}' alfagift-ios-cicd/Info.plist"
//             sh "gem install bundler"
//             sh "bundle install"
//             sh "bundle exec fastlane pod_install"
//             sh "ruby -r dotenv/load -e \"Dotenv.load('.env.debug')\""
//             // sh "ruby -r dotenv/load -e \"Dotenv.load('.env.${params.ENV_CONFIG}')\""
//         }
//     }

    stages {
        stage('Clean Gradle Cache') {
            steps {
              sh 'gem -v'
              dir(env.WORKSPACE) {
                  sh "echo fastlane runClean"
              }

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