package play.abilities;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Ability {
    public void useAbility() throws JsonProcessingException;
}
