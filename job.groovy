job("day6/MNTLAB-aausiankin-main-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script('''def gitURL = "https://github.com/ovsyankinaa/jenkins-dsl.git"
def command = "git ls-remote -h $gitURL"

def proc = command.execute()
proc.waitFor()

if ( proc.exitValue() != 0 ) {
  println "Error, ${proc.err.text}"
  System.exit(-1)
}

def branches = proc.in.text.readLines().collect {
  it.replaceAll(/[a-z0-9]*\\trefs\\/heads\\//, '')
}

return branches
''')
        fallbackScript()
      }
    }
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
          configs {
            predefinedBuildParameters {
              properties('BRANCH_NAME=$BRANCH_NAME')
              textParamValueOnNewLine(false)
            }
          } 
        }
      }
    } 
  }
}

job("day6/MNTLAB-aausiankin-child1-build-job"){
  parameters {
    stringParam('BRANCH_NAME', '', 'Branche name')
  }
  scm {
//    git('https://github.com/ovsyankinaa/jenkins-dsl.git', '$BRANCH_NAME')
    git('${GIT_URL}')
  }
  steps {
    shell('chmod +x ./script.sh && ./script.sh > result.txt && tar -czf artifact.tar.gz result.txt script.sh')
  }  
  publishers {
    archiveArtifacts {
      pattern('artifact.tar.gz')
      onlyIfSuccessful()
    }
  }
}

job("day6/MNTLAB-aausiankin-child2-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'main', 'Branche name')
  }
  steps {
    shell('sleep 20')
  }  
}

job("day6/MNTLAB-aausiankin-child3-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'main', 'Branche name')
  }
  steps {
    shell('sleep 20')
  }  
}

job("day6/MNTLAB-aausiankin-child4-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'main', 'Branche name')
  }
  steps {
    shell('sleep 20')
  }  
}
