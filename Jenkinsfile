pipeline {
    agent any 
    stages {
        stage('Test') {
            steps {
                sh ' docker run -e SourceType=false -e SourceHost=192.168.205.6 -e SourcePort=7001 -e SourcePassword="" -e DestinationType=true -e DestinationHost=192.168.205.7 -e DestinationPort=8001 -e DestinationPassword="" sanganimukesh01/redis_shake'
            }
        }
    }
}
