pipeline {
  agent any
  options {
    skipStagesAfterUnstable()
  }

  stages {
    stage('Build') {
      steps {
        script {
          def scmVars = checkout([
            $class: 'GitSCM',
            branches: [[name: '*/master']],
            userRemoteConfigs: [[url: 'git@github.com:sesco-llc/caisocrrclient.git']]
          ])

          echo "scmVars.GIT_COMMIT: ${scmVars.GIT_COMMIT}"
      
	      env.GIT_COMMIT = scmVars.GIT_COMMIT
          echo "env.GIT_COMMIT: ${env.GIT_COMMIT}"		

          env.JAVA_HOME = "/app/java/zulu-jdk.8.0.362"
        }

        withAnt(installation: 'Ant1.6.1') {
          sh "ant build.dist.publish"
        } 
      }
    }
  }
}

