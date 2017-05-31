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


####Given the above feed into an organization, find out how many
    - People have skill : Java with BigData skills
    - People have skill : Fidget-Spinning

#### Solution:
- People have skill Java with BigData  --> send to my-skills topic
- People have skill Fidget Spinning  --> send to my-other-skills topic
- Count of people as and when received, 
    - who has Java with BigData skills --> send to my-stats topic 
    - who has Fidget-Spinning skills --> send to my-stats topic 

####How to install/start/stop ?
bin/grid bootstrap   <-----------    - If you are doing it first time

If not first time, issue the following commands individually. It picks from the installs folder and puts into the /hello-kafka-samza/deploy folder.
        - bin/grid install kafka
        - bin/grid install zookeeper
        - bin/grid install yarn

You should see output like below:
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
bin/grid start all

Now go to "How to deploy, run, observe the output?" section of the "README_hello_kafka_samza.txt"
(Note- for windows OS, look and map for similar folders)

