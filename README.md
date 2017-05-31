hello-kafka-samza
=================

Simplified version for developers and testers to understand how the streaming pipelines works.

- Git repo:

#### Feed details: People, Place, Things and Connections/Relationship Between them.
- Given the people feed into an organization wit the following data.
-- People have skill Java with BigDaata skills
-- People have skill Python
-- People have skill BigData


####Given the above feed into an organization, find out how many
-- People have skill : Java with BigData skills
-- People have skill : Fidget-Spinning

#### Solution:
- People have skill Java with BigData  --> send to my-skills topic
- People have skill Fidget Spinning  --> send to my-other-skills topic
- Count of people as and when received, 
-- who has Java with BigData skills --> send to my-stats topic 
-- who has Fidget-Spinning skills --> send to my-stats topic 

