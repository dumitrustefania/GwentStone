package play.action.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

public class LordRoyce extends HeroAbility{

    public LordRoyce(ArrayList<CardInput> affectedRow, Game game) {
        super(affectedRow, game);
    }

    @Override
    public void useAbility() {
        CardInput selectedCard = null;
        int maxDamage = 0;
        for(CardInput card : affectedRow) {
            if(card.getAttackDamage() > maxDamage) {
                maxDamage = card.getAttackDamage();
                selectedCard = card;
            }
        }

        game.getFrozenCards().add(selectedCard);
    }
}
