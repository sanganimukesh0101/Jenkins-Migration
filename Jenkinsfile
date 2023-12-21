pipeline {
    agent any 
    stages {
        stage('Test') {
            steps {
                script {
                    sh 'docker --version'
                    sh 'docker run -e SourceType=false -e SourceHost=192.168.205.6 -e SourcePort=7001 -e SourcePassword="" -e DestinationType=true -e DestinationHost=192.168.205.7 -e DestinationPort=8001 -e DestinationPassword="" -d sanganimukesh01/redis_shake'

                    echo "Checking path: ${WORKSPACE}/percentage.groovy"

                    def gv = load "${WORKSPACE}/percentage.groovy"

                    if (gv != null) {
                        echo "Script loaded successfully"
                        gv.completedPercentage()
                    } else {
                        echo "Failed to load percentage.groovy"
                    }
                }
            }
        }
    }
}


