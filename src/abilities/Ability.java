package abilities;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Ability {
    /**
     * use card's ability
     * @throws JsonProcessingException
     */
    void useAbility() throws JsonProcessingException;
}
