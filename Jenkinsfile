// TODO: global variables

podTemplate(
  inheritFrom: "jenkins-inbound-agent",
  containers: [
    containerTemplate(name: "java-container", image: "fabric8/maven-builder:v4e87df6", alwaysPullImage: false, ttyEnabled: true, command: "cat"),
    containerTemplate(name: "docker-container", image: "docker:19.03.1-dind", alwaysPullImage: false, ttyEnabled: true, command: "cat"),
  ]
) {
  node(POD_LABEL) {
    stage("CodeCheckout_1") {
      checkout([$class: "GitSCM", userRemoteConfigs: [[url: "https://github.com/samar-b/Continuous-integration-project"]], branches: [[name: "*/main"]]])
    }
    stage("BuildAndTestJava_1") {
      container("java-container") {
        sh """
          mvn -f "./pom.xml" clean install
        """
      }
    }
    stage("DockerBuild_1") {
      container("docker-container") {
          withCredentials([[credentialsId: "docker-config", variable: "DOCKER_CONFIG"]]) {
            sh """
              docker build -t "samarbelhadj/testleto:1.0"  -f Dockerfile .
            """
          }
        
      }
    }
    stage("DockerPush_1") {
      container("docker-container") {
          withCredentials([[ credentialsId: "docker-config", variable: "DOCKER_CONFIG"]]) {
            sh """
              docker push "samarbelhadj/testleto:1.0"
            """
          }
        
      }
    }
  }
}
