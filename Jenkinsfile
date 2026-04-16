@Library("JenkinsLibrary") _
pipeline {
    agent any

    options {
        skipStagesAfterUnstable()
    }

    environment {
        APP_NAME='caisocrrclient'
        TAG_PREFIX = 'v'
        JOB_SERVER = "ponos.sesco.com"
        USER = "sescojobs"
        HOST_KEY = "~/.ssh/sescojobs-rsa"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    new CommonBuild(this, env.APP_NAME, CommonBuild.JDK.ZULU_8).execute()
                }
            }
        }
        stage('Deploy') {
            when {
                beforeAgent true
                expression {
                    def tag = sh(script: 'git describe --tags --exact-match --abbrev=0 HEAD || echo "NOTAG"', returnStdout: true).trim()
                    echo "tag: ${tag}"
                    return tag ==~ /^${env.TAG_PREFIX}.*$/  // Check if tag matches pattern
                }
            }
            steps {
                script {
                    new DeployClientApp(this, env.APP_NAME).execute()
                    new DeployJar(this, env.APP_NAME, env.JOB_SERVER, env.USER, env.HOST_KEY).execute()
                }
            }

        }
    }
}



pipeline {
  agent any
  options {
    skipStagesAfterUnstable()
  }


