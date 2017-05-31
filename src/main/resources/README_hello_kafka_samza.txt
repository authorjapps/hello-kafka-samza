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

-- bin/produce-my-stats-fidget-data.sh    <----- The see the my-stats topic


        > bin/grid stop all  --> Then check:

-- kill -9 (kafka_id) --> this will kill samza and zookeeper process also.

-- For clearing kafka topics:
    -- go to below folder and delete the fields, but first stop the containers ie bin/grid stop all 
    
-- In case you want to see howmany jobs running, issue the following command.
    >> deploy/yarn/bin/yarn application --list

-- In case you want kill a job/application you have already submitted and listed above, issue the following command.
    >> deploy/yarn/bin/yarn application -kill <application_1495207344947_0001>   <------ pick this ID from above list command


----------------------------------------------------------------------------------------
Issue(if you face) resolved:
----------------------------------------------------------------------------------------
    -- Tried with these two properties as "java.net.InetAddress.getCanonicalHostName()." returned "unknown"
        -- ie output of this:       System.out.println("------InetAddress.getLocalHost().getCanonicalHostName(): " + InetAddress.getLocalHost().getCanonicalHostName());

        # updated in kafka/config/server.properties
        listeners = PLAINTEXT://localhost:9092
        advertised.listeners=PLAINTEXT://localhost:9092
