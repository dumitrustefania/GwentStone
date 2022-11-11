package play.abilities.environment_abilities;

import fileio.CardInput;
import play.Game;
import util.JSONout;

import java.util.ArrayList;

public class Winterfell extends EnvironmentAbility{
    public Winterfell(ArrayList<CardInput> affectedRow, Game game, CardInput envCard, JSONout out) {
        super(affectedRow, game, envCard, out);
    }

    @Override
    public void useAbility() {
        for(CardInput card: affectedRow) {
            if(!game.getFrozenCards().contains(card))
                game.getFrozenCards().add(card);
        }

        game.getCurrentPlayer().getHand().remove(envCard);
        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
