package samza.examples.hello.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.OutgoingMessageEnvelope;
import org.apache.samza.system.SystemStream;
import org.apache.samza.task.MessageCollector;
import org.apache.samza.task.StreamTask;
import org.apache.samza.task.TaskCoordinator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import samza.examples.hello.domain.PeopleSkillEvent;

import static samza.examples.hello.system.SkillUtils.hasJavaAndBigDataSkills;

public class MySkillsStreamTask implements StreamTask {
    private static final Logger LOG = LoggerFactory.getLogger(MySkillsStreamTask.class);
    
    private static final SystemStream OUTPUT_SKILL_STREAM = new SystemStream("kafka", "my-skills");
    private static final SystemStream OUTPUT_OTHER_STREAM = new SystemStream("kafka", "my-other-skills");
    
    public void process(
                    IncomingMessageEnvelope envelope,
                    MessageCollector collector,
                    TaskCoordinator coordinator) throws Exception {
        
        final Object message = envelope.getMessage();
        LOG.info("---message--->\n  " + message);
        
        final PeopleSkillEvent event = new ObjectMapper().convertValue(message, PeopleSkillEvent.class);
        LOG.info(">>> mapped to people skill event >>>>>> : " + event);
        
        Map<String, Object> skillMap = new HashMap<>();
    
    
        
        if (event.getSkills() != null) {
    
            if(hasJavaAndBigDataSkills(event.getSkills())){
                collector.send(new OutgoingMessageEnvelope(OUTPUT_SKILL_STREAM, event));
            } else{
                collector.send(new OutgoingMessageEnvelope(OUTPUT_OTHER_STREAM, event));
            }
        }
        
    }
    
}