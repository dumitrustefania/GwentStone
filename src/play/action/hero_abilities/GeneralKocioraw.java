package play.action.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

public class GeneralKocioraw extends HeroAbility{

    public GeneralKocioraw(ArrayList<CardInput> affectedRow, Game game) {
        super(affectedRow, game);
    }

    @Override
    public void useAbility() {
        for(CardInput card : affectedRow)
            card.setAttackDamage(card.getAttackDamage() + 1);
    }
}