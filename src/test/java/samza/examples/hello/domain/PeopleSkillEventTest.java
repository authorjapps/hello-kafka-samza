package samza.examples.hello.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class PeopleSkillEventTest {
    
    @Test
    public void testSerDe() throws Exception {
                        
        final PeopleSkillEvent peopleSkillEvent =
                        new ObjectMapper().readValue("{\"name\":\"Rosh\",\"place\":\"London\",\"skills\":[{\"subject\":\"java\",\"experience\":5,\"where\":\"Banking\"},{\"subject\":\"BigData\",\"experience\":2,\"where\":\"Govt\"},{\"subject\":\"Fidget Spinning\",\"experience\":1,\"where\":\"London\"}],\"time\":1398926301271,\"source\":\"fd-lon\",\"channel\":\"#jobserve\"}\n",
                        PeopleSkillEvent.class);
    
        Assert.assertThat(peopleSkillEvent.getName(), is("Rosh"));
    }
    
    @Test
    public void testJackson() throws Exception {
        Map<String, Object> skill = new HashMap<>();
        skill.put("subject", "java");
        skill.put("experience", 5);
        skill.put("where", "Bank");
        
        Map<String, Object> message = new HashMap<>();
        message.put("name", "Rosh");
        message.put("skills", Arrays.asList(skill));
        
        final ObjectMapper mapper = new ObjectMapper();
        final PeopleSkillEvent event = mapper.convertValue(message, PeopleSkillEvent.class);
    
        Assert.assertThat(event.getName(), is("Rosh"));
        Assert.assertThat(event.getSkills().get(0).getSubject(), is("java"));
        Assert.assertThat(event.getSkills().get(0).getExperience(), is(5));
    
        
    }
}