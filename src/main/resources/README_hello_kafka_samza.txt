Hello KAFKA / SAMZA:

- Objective:
    -- Publish raw JSON with java, bigData, Fidget Spinning skills to my-raw topic. Command:
    /hello-samza-13/hello-samza$ bin/produce-my-stats-fidget-data.sh

    -- Listen to my-raw topic and filter Java with BigData skills and produce/publish to my-skills topic. Command:
    /hello-samza-13/hello-samza$ deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_skills.properties

    -- Process javaWithBigData count an dpublish to my-stats topic. Command:
    /hello-samza-13/hello-samza$ deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_stats.properties

    -- This shd print only "javaAndBigData":
    {"fidgetSpinning":0,"edits-all-time-count":8,"javaAndBigData":8}

    -- Publish raw JSON with Fidget Spinning skills to my-skills topic directly. Command:
    /hello-samza-13/hello-samza$ bin/produce-my-stats-fidget-data.sh

    -- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-stats
    {"fidgetSpinning":12,"edits-all-time-count":24,"javaAndBigData":16}


-- mvn clean package
-- mkdir -p deploy/samza
-- tar -xvf ./target/hello-samza-0.12.0-dist.tar.gz -C deploy/samza

        > bin/grid start all  --> Then check all containers started ie yarn, kafka, samza, zookeeper


-- bin/produce-my-raw-data.sh
-- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-raw

-- deploy/samza/bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=file://$PWD/deploy/samza/config/my_task.properties
-- deploy/kafka/bin/kafka-console-consumer.sh  --zookeeper localhost:2181 --topic my-edit

-- See logs:
        -- /Users/nirmalchandra/dev/http_clone/github/hello-samza-13/hello-samza/deploy/yarn/logs/userlogs/application_1495364118438_0001/container_1495364118438_0001_01_000002/samza-container-0.log    <----- for log

        -- /Users/nirmalchandra/dev/http_clone/github/hello-samza-13/hello-samza/deploy/yarn/logs/userlogs/application_1495364118438_0001/container_1495364118438_0001_01_000002/stdout   <----- For sysout.println messages

        > bin/grid stop all  --> Then check:

-- kill -9 (kafka_id) --> this will kill samza and zookeeper process also if running.

-- For clearing kafka topics:
    -- go to below folder and delete the fiels, but first stop the containers ie <bin/grid stop all>  before deleting the files.
    -- You will find a folder per topic. Delete the contents of  /private/tmp/kafka-logs  including all those topic folders
    -- /private/tmp/zookeeper/version-2  : Delete the contents of this folder also.


-- deploy/kafka/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic my-raw
    -- Displays the latests messages published to this topic
    -- If you stop publishing to this topic, then this command will show nothing

-- deploy/kafka/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic my-raw --from-beginning
    -- Even displays all messages so far been published. from the 1st message to last message.


Issue resolved
    -- Tried with these two properties as "java.net.InetAddress.getCanonicalHostName()." returned "unknown"
        -- ie output od this:       System.out.println("------InetAddress.getLocalHost().getCanonicalHostName(): " + InetAddress.getLocalHost().getCanonicalHostName());

        # updated in kafka/config/server.properties
        listeners = PLAINTEXT://localhost:9092
        advertised.listeners=PLAINTEXT://localhost:9092
