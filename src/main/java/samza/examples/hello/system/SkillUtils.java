package samza.examples.hello.system;

import java.util.List;

import samza.examples.hello.domain.Skill;

public class SkillUtils {
    public static boolean hasJavaAndBigDataSkills(List<Skill> skills){
        boolean hasJava = skills.stream().anyMatch(item -> "JAVA".equals(item.getSubject().toUpperCase()));
        boolean hasBigData = skills.stream().anyMatch(item -> "BIGDATA".equals(item.getSubject().toUpperCase()));
    
        return hasJava && hasBigData;
    }
    
    public static boolean hasFidgetSpinningSkills(List<Skill> skills){
        return skills.stream().anyMatch(item -> item.getSubject().toUpperCase().indexOf("SPINNING") >= 0);
        
    }
}
