job("day6/MNTLAB-aausiankin-main-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'jenkins-dsl', 'Branche name')
    activeChoiceParam('JOBS_NAMES') {
      description('User can choose jobs for execution')
        choiceType('CHECKBOX')
        groovyScript {
        script('["MNTLAB-aausiankin-child1-build-job", "MNTLAB-aausiankin-child2-build-job", "MNTLAB-aausiankin-child3-build-job", "MNTLAB-aausiankin-child4-build-job"]')
        fallbackScript()
      }
    }
  }  
  concurrentBuild()
  buildTrigger {
    configs {
      buildTriggerConfig {
        projects($CHILD_JOBS_NAMES)
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
