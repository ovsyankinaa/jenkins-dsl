job("day6/MNTLAB-aausiankin-main-build-job"){
  parameters {
    activeChoiceParam('BRANCH_NAME') {
      description('Branch name')
        choiceType('SINGLE_SELECT')
        groovyScript {
        script("""def gitURL = "$GIT_URL"
def command = "git ls-remote -h \$gitURL"

def proc = command.execute()
proc.waitFor()

if ( proc.exitValue() != 0 ) {
  println "Error, \${proc.err.text}"
  System.exit(-1)
}

def branches = proc.in.text.readLines().collect {
  it.replaceAll(/[a-z0-9]*\\trefs\\/heads\\//, '')
}

return branches
""")
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

for(int i = 1;i<5;i++) {
  job("day6/MNTLAB-aausiankin-child${i}-build-job"){
    parameters {
      stringParam('BRANCH_NAME', '', 'Branche name')
    } 
    scm {
      git("${GIT_URL}", '$BRANCH_NAME')
    }
    steps {
      shell("chmod +x ./script.sh && ./script.sh > result.txt && tar -czf ${BRANCH_NAME}_dsl_script.tar.gz result.txt script.sh")
    }  
    publishers {
      archiveArtifacts {
        pattern("${BRANCH_NAME}_dsl_script.tar.gz")
        onlyIfSuccessful()
      }
    }
  }
}

