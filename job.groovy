job("day6/MNTLAB-aausiankin-main-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'jenkins-dsl', 'Branche name')
    activeChoiceParam('choose jobs for execution') {
      description('User can choose jobs for execution')
        choiceType('CHECKBOX')
        groovyScript {
        script('["MNTLAB-aausiankin-child1-build-job", "MNTLAB-aausiankin-child2-build-job", "MNTLAB-aausiankin-child3-build-job", "MNTLAB-aausiankin-child4-build-job"]')
        fallbackScript()
      }
    }
  }  
}

job("day6/MNTLAB-aausiankin-child1-build-job"){
  
}

job("day6/MNTLAB-aausiankin-child2-build-job"){
  
}
  
job("day6/MNTLAB-aausiankin-child3-build-job"){
  
}

job("day6/MNTLAB-aausiankin-child4-build-job"){
  
}
