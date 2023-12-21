def completedPercentage() {
    while (true) {
        def percentage
        def sourceKeys = total_keys("192.168.205.6", 7001, false)
        def destinationKeys = total_keys("192.168.205.7", 8001, true)

            percentage = destinationKeys * 100 / sourceKeys
            println("$percentage%")
        
        sleep(1000)
    }
}

def total_keys_in_each_master(masterIp, masterPort) {
    def keys = 0
    def subcommand = "redis-cli -h $masterIp -p $masterPort info keyspace"
    def subprocess = subcommand.execute()

    subprocess.waitFor()
    
    def output1 = subprocess.text
    def keysInfoLine = output1.split('\n').find { it.startsWith("db0:keys=") }
    def keysCount = keysInfoLine?.replaceAll("db0:keys=(\\d+).*", "\$1")?.trim() ?: "0"

    keys += keysCount.toInteger() 

    return keys
}

def total_keys(Ip, Port, cluster) {
    
    def totalKeys = 0 
    def masterIps = []
    def masterPorts = []

    if (cluster) {
        def command = "redis-cli -h $Ip -p $Port cluster nodes"
        def process = command.execute()

        process.waitFor()

        def output = process.text

        
        def lines = output.split("\n")

       
        lines.each { line ->
            if (line.contains("master")) {
                def parts = line.split(" ")
                def ipAndPort = parts[1]
                def ipPortParts = ipAndPort.split(":")
                def masterIp = ipPortParts[0]
                def masterPortParts = ipPortParts[1].split("@")
                def masterPort = masterPortParts[0]
                masterIps.push(masterIp)
                masterPorts.push(masterPort)
            }
        }
    } else {
        masterIps.push(Ip)
        masterPorts.push(Port)
    }

    for (int i = 0; i < masterIps.size(); i++) {
        totalKeys += total_keys_in_each_master(masterIps[i], masterPorts[i])
    }

    return totalKeys
}


