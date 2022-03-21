job("day6/MNTLAB-aausiankin-main-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'jenkins-dsl', 'Branche name')
    activeChoiceParam('CHILD_JOBS_NAMES') {
      description('User can choose jobs for execution')
        choiceType('CHECKBOX')
        groovyScript {
        script('["MNTLAB-aausiankin-child1-build-job", "MNTLAB-aausiankin-child2-build-job", "MNTLAB-aausiankin-child3-build-job", "MNTLAB-aausiankin-child4-build-job"]')
        fallbackScript()
      }
    }
  }  
  concurrentBuild()
  steps {
    triggerBuilder {
      configs {
        blockableBuildTriggerConfig {
          projects('$CHILD_JOBS_NAMES')
          block {
            buildStepFailureThreshold('FAILURE')
            unstableThreshold('UNSTABLE')
            failureThreshold('FAILURE')
          } 
        }
      }
    } 
  }
}

job("day6/MNTLAB-aausiankin-child1-build-job"){
  steps {
    shell('sleep 20')
  }  
}

job("day6/MNTLAB-aausiankin-child2-build-job"){
  steps {
    shell('sleep 20')
  }  
}
  
job("day6/MNTLAB-aausiankin-child3-build-job"){
  steps {
    shell('sleep 20')
  }  
}

job("day6/MNTLAB-aausiankin-child4-build-job"){
  steps {
    shell('sleep 20')
  }  
}
