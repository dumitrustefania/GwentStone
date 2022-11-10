package play.action.environment_abilities;

import fileio.CardInput;
import jdk.security.jarsigner.JarSigner;
import play.Game;
import util.JSONout;

import java.util.ArrayList;

public class Firestorm extends EnvironmentAbility{
    public Firestorm(ArrayList<CardInput> affectedRow, Game game, CardInput envCard, JSONout out) {
        super(affectedRow, game, envCard, out);
    }

    @Override
    public void useAbility() {
        for(CardInput card: affectedRow)
            card.setHealth(card.getHealth() - 1);

        game.getCurrentPlayer().getHand().remove(envCard);
    }
}
