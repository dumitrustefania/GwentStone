package play.abilities;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 */
public interface Ability {
    /**
     * add comm
     * @throws JsonProcessingException
     */
    void useAbility() throws JsonProcessingException;
}
