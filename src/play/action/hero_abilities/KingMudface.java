package play.action.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

public class KingMudface extends HeroAbility{

    public KingMudface(ArrayList<CardInput> affectedRow, Game game) {
        super(affectedRow, game);
    }

    @Override
    public void useAbility() {
        for(CardInput card : affectedRow)
            card.setHealth(card.getHealth() + 1);
    }
}