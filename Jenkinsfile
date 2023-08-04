pipeline {
    agent any

    tools{
      gradle 'gradle_for_android'
    }

    environment {
      LANG = 'en_US.UTF-8'
      LC_ALL = 'en_US.UTF-8'
      // // Rbenv VM Path Configuration
      PATH = "/Users/gli-mac/.rbenv/shims:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin"
      ANDROID_HOME = '/Users/gli-mac/Library/Android/sdk'
    }



    stages {
        stage('Configure Environment') {
            steps {
                // sh "echo plutil -replace ReleaseName -string '${params.BASE_URL}' alfagift-ios-cicd/Info.plist"
                sh "gem install bundler"
                sh "bundle install"
                sh 'java -version'
                // sh "ruby -r dotenv/load -e \"Dotenv.load('.env.${params.ENV_CONFIG}')\""
            }
        }

        stage('Clean Gradle Cache') {
            steps {
              sh 'gem -v'
              sh "chmod +x gradlew"
              sh "bundle exec fastlane runClean"
            }
        }
//
        stage('Unit Tests') {
            steps {
              sh "bundle exec fastlane runUnitTest"
            }
        }
//
        stage('Compile & Build APK') {
            steps {
              sh 'bundle exec fastlane runBuildApk'
            }
        }
//
        stage('Upload to Firebase') {
            steps {
              sh 'bundle exec fastlane distribute'
            }
        }
    }
}