podTemplate(
  inheritFrom: "jenkins-agent",
  containers: [
     containerTemplate(name: "java-container", image: "maven:3.8.1-openjdk-11-slim", alwaysPullImage: false, ttyEnabled: true, command: "cat"),
    containerTemplate(name: "docker-container", image: "docker:19.03.1-dind", alwaysPullImage: false, ttyEnabled: true, command: "cat")
  ]
) {
  node(POD_LABEL) {
    stage("CodeCheckout_1") {
      checkout([$class: "GitSCM", userRemoteConfigs: [[url: "https://github.com/samar-b/Continuous-integration-project"]], branches: [[name: "*/main"]]])
    }
    stage("BuildAndTestJava_1") {
      container(name: "java-container") {
        sh '''
          mvn -f \"./pom.xml\" clean install -DskipTests
        '''
      }
    }
    
    
   
    
    
    stage("DockerBuild_1") {
      container(name: "docker-container") {
        withCredentials([file(credentialsId: 'docker-config', variable: 'DOCKERH_CONFIG')]) {
 
      sh '''
        docker build -t "samarbelhadj/testleto:1.0" -f Dockerfile .
      '''
    }
  }
}

    stage("DockerPush_1") {
      container(name: "docker-container") {
          withCredentials([file(credentialsId: 'docker-config', variable: 'DOCKERH_CONFIG')])
          {
          sh '''
            docker push "samarbelhadj/testleto:1.0"
          '''
        }
      }
    }
  
}
}
