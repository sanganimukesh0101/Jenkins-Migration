pipeline {
    agent {
        docker {
            image 'sanganimukesh01/redis_shake'
            args '-e SourceType=false -e SourceHost=192.168.205.6 -e SourcePort=7001 -e SourcePassword="" -e DestinationType=true -e DestinationHost=192.168.205.7 -e DestinationPort=8001 -e DestinationPassword=""'
        }
    }
    stages {
        stage('Test') {
            steps {
                echo 'Good'
            }
        }
    }
}
