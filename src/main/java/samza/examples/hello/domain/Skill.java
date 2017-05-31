package samza.examples.hello.domain;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Skill{
    private String subject;
    private Integer experience;
    private String where;
    
    @JsonCreator
    public Skill(
                    @JsonProperty("subject") String subject,
                    @JsonProperty("experience") Integer experience,
                    @JsonProperty("where") String where) {
        this.subject = subject;
        this.experience = experience;
        this.where = where;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public Integer getExperience() {
        return experience;
    }
    
    public String getWhere() {
        return where;
    }
    
    @Override
    public String toString() {
        return "Skill{" +
               "subject='" + subject + '\'' +
               ", experience='" + experience + '\'' +
               ", where='" + where + '\'' +
               '}';
    }
}