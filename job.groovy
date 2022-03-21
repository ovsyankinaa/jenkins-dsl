job("MNTLAB-aausiankin-main-build-job"){
  parameters {
    stringParam('BRANCH_NAME', 'jenkins-dsl', 'Branche name')
    activeChoiceParam('choose jobs for execution') {
      description('User can choose jobs for execution')
        filterable()
        choiceType('CHECKBOX')
        groovyScript {
        script('["MNTLAB-aausiankin-child1-build-job", "MNTLAB-aausiankin-child2-build-job", "MNTLAB-aausiankin-child3-build-job", "MNTLAB-aausiankin-child4-build-job"]')
        fallbackScript()
      }
    }
  }  
}

job(MNTLAB-aausiankin-child1-build-job){
  
}

job(MNTLAB-aausiankin-child2-build-job){
  
}
  
job(MNTLAB-aausiankin-child3-build-job){
  
}

job(MNTLAB-aausiankin-child4-build-job){
  
}
