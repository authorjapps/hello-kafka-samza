hello-kafka-samza
=================

Simplified version for developers and testers to understand how the streaming pipelines works.
This is a bit of Hello World Kafka and Hello World Samza kind of example

- Git repo: 
    - URL: https://github.com/authorjapps/hello-kafka-samza
    - Clone: https://github.com/authorjapps/hello-kafka-samza.git
    - Also read this file at this folder for more details(if you want to): hello-kafka-samza/src/main/resources/README_hello_kafka_samza.txt

#### Feed details: People, Place, Things and Connections/Relationship Between them.
- Given the people feed into an organization wit the following data.
    - People have skill Java with BigDaata skills
    - People have skill Python
    - People have skill BigData


#### Given the above feed into an organization, find out how many
- People have skill : Java with BigData skills
- People have skill : Fidget-Spinning

#### Solution:
- People have skill Java with BigData  --> send to my-skills topic
- People have skill Fidget Spinning  --> send to my-other-skills topic
- Count of people as and when received, 
    - who has Java with BigData skills --> send to my-stats topic 
    - who has Fidget-Spinning skills --> send to my-stats topic 

#### How to install/start/stop ?
bin/grid bootstrap   <-----------    - If you are doing it first time

If not first time, issue the following commands individually. It picks from the installs folder and puts into the /hello-kafka-samza/deploy folder.
- bin/grid install kafka
- bin/grid install zookeeper
- bin/grid install yarn

- You should see output like below:

$pwd
../hello-kafka-samza

$ bin/grid install kafka
EXECUTING: install kafka
Using previously downloaded file /Users/nchandra/.samza/download/kafka_2.11-0.10.0.1.tgz

$ bin/grid install zookeeper
EXECUTING: install zookeeper
Using previously downloaded file /Users/nchandra/.samza/download/zookeeper-3.4.3.tar.gz

$ bin/grid install yarn
EXECUTING: install yarn
Using previously downloaded file /Users/nchandra/.samza/download/hadoop-2.6.1.tar.gz
$ 

Then:
- bin/grid start all
- (Note- for windows OS, look and map for similar folders)
- Now read the "How to deploy, run, observe the output?" section of the "README_hello_kafka_samza.txt" ro read the below

```
Hello KAFKA / SAMZA:
----------------------------------------------------------------------------------------
- Objective:
----------------------------------------------------------------------------------------
    -- Publish raw JSON with java, bigData, Fidget Spinning skills to my-raw topic. Command:
    /hello-samza-13/hello-kafka-samza$ bin/produce-my-stats-fidget-data.sh

    -- Listen to my-raw topic and filter Java with BigData skills and produce/publish to my-skills topic. Command:
    /hello-kafka-samza-13/hello-kafka-samza$ deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_skills.properties

    -- Process javaWithBigData count an dpublish to my-stats topic. Command:
    /hello-kafka-samza-13/hello-kafka-samza$ deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_stats.properties

    -- This shd print only "javaAndBigData":
    {"fidgetSpinning":0,"edits-all-time-count":8,"javaAndBigData":8}

    -- Publish raw JSON with Fidget Spinning skills to my-skills topic directly. Command:
    /hello-kafka-samza-13/hello-kafka-samza$ bin/produce-my-stats-fidget-data.sh

    -- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-stats
    {"fidgetSpinning":12,"edits-all-time-count":24,"javaAndBigData":16}

----------------------------------------------------------------------------------------
How to deploy, run, observe the output?
----------------------------------------------------------------------------------------
-- mvn clean package
-- mkdir -p deploy/samza
-- tar -xvf ./target/hello-samza-0.12.0-dist.tar.gz -C deploy/samza

        > bin/grid start all  --> Then check all containers started ie yarn, kafka, samza, zookeeper

-- bin/produce-my-raw-data.sh   
-- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-raw

-- deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_skills.properties

-- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-skills
-- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-other-skills

-- See logs:
        -- /Users/nchandra/dev/http_clone/github/hello-samza-13/hello-samza/deploy/yarn/logs/userlogs/application_1495364118438_0001/container_1495364118438_0001_01_000002/samza-container-0.log    <----- for log

        -- /Users/nchandra/dev/http_clone/github/hello-samza-13/hello-samza/deploy/yarn/logs/userlogs/application_1495364118438_0001/container_1495364118438_0001_01_000002/stdout   <----- For sysout.println messages

-- deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_stats.properties

-- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-stats

-- bin/produce-my-stats-fidget-data.sh    <----- Then see whats produced to my-stats topic

        > bin/grid stop all  <------ Once you are done, you can stop all containers. Then check if any process running:

-- kill -9 (kafka_id) --> this will kill samza and zookeeper process also.

-- For clearing kafka topics:
    -- go to below folder and delete the fields, but first stop the containers ie bin/grid stop all 
    
-- In case you want to see howmany jobs running, issue the following command.
    >> deploy/yarn/bin/yarn application --list

-- In case you want kill a job/application you have already submitted and listed above, issue the following command.
    >> deploy/yarn/bin/yarn application -kill <application_1495207344947_0001>   <------ pick this ID from above --list command


----------------------------------------------------------------------------------------
Issue(if you face) resolved:
----------------------------------------------------------------------------------------
    -- Tried with these two properties as "java.net.InetAddress.getCanonicalHostName()." returned "unknown"
        -- ie output of this:       System.out.println("------InetAddress.getLocalHost().getCanonicalHostName(): " + InetAddress.getLocalHost().getCanonicalHostName());

        # updated in kafka/config/server.properties
        listeners = PLAINTEXT://localhost:9092
        advertised.listeners=PLAINTEXT://localhost:9092
```

