package play.abilities.environment_abilities;

import fileio.CardInput;
import play.Game;
import util.JSONout;

import java.util.ArrayList;

public class Firestorm extends EnvironmentAbility{
    public Firestorm(ArrayList<CardInput> affectedRow, Game game, CardInput envCard, JSONout out) {
        super(affectedRow, game, envCard, out);
    }

    @Override
    public void useAbility() {
        for (int i = affectedRow.size() - 1; i >= 0 ; i--){
            CardInput card = affectedRow.get(i);
            card.setHealth(card.getHealth() - 1);
            if(card.getHealth() <= 0) {
                affectedRow.remove(card);
                game.getFrozenCards().remove(card);
            }

        }

        game.getCurrentPlayer().getHand().remove(envCard);
        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() - envCard.getMana());
    }
}