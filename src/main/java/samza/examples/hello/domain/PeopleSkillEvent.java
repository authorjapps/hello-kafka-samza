package samza.examples.hello.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PeopleSkillEvent {
    private String name;
    
    private String place;
    
    private List<Skill> skills;
    
    private String time;
    
    private String source;
    
    private String channel;
    
    @JsonCreator
    public PeopleSkillEvent(
                    @JsonProperty("name") String name,
                    @JsonProperty("place") String place,
                    @JsonProperty("skills") List<Skill> skills,
                    @JsonProperty("time") String time,
                    @JsonProperty("source") String source,
                    @JsonProperty("channel") String channel) {
        this.name = name;
        this.place = place;
        this.skills = skills;
        this.time = time;
        this.source = source;
        this.channel = channel;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPlace() {
        return place;
    }
    
    public List<Skill> getSkills() {
        return skills;
    }
    
    public String getTime() {
        return time;
    }
    
    public String getSource() {
        return source;
    }
    
    public String getChannel() {
        return channel;
    }
    
    @Override
    public String toString() {
        return "PeopleSkillEvent{" +
               "name='" + name + '\'' +
               ", place='" + place + '\'' +
               ", skills=" + skills +
               ", time='" + time + '\'' +
               ", source='" + source + '\'' +
               ", channel='" + channel + '\'' +
               '}';
    }
    
    
}


/*


{
    "name": "Rosh",
    "place": "London",
    "skills": [
        {
            "subject": "java",
            "experience": 5,
            "where": "Banking"
        },
        {
            "subject": "BigData",
            "experience": 2,
            "where": "Govt"
        },
        {
            "subject": "Fidget Spinning",
            "experience": 1,
            "where": "London"
        }
    ],
    "time": 1398926301271,
    "source": "fd-lon",
    "channel": "#jobserve"
}
 
 */