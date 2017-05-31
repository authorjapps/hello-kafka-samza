package samza.examples.hello.domain;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SkillTest {
    
    @Test
    public void testSerDe() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        final Skill skill = mapper.readValue("{\n"
                                                        + "            \"subject\": \"java\",\n"
                                                        + "            \"experience\": 5,\n"
                                                        + "            \"where\": \"Banking\"\n"
                                                        + "        }",
                        Skill.class);
    
        Assert.assertThat(skill.getSubject(), is("java"));
        Assert.assertThat(skill.getExperience(), is(5));
    
    }
    
    @Test
    public void testJackson() throws Exception {
        Map<String, Object> message = new HashMap<>();
        message.put("subject", "java");
        message.put("experience", 5);
        message.put("where", "Bank");
        
        final ObjectMapper mapper = new ObjectMapper();
        final Skill event = mapper.convertValue(message, Skill.class);
    
        Assert.assertThat(event.getSubject(), is("java"));
        Assert.assertThat(event.getExperience(), is(5));
    
    }
}