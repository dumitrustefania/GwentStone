package play.action.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

public class EmpressThorina extends HeroAbility{

    public EmpressThorina(ArrayList<CardInput> affectedRow, Game game) {
        super(affectedRow, game);
    }

    @Override
    public void useAbility() {
        CardInput selectedCard = null;
        int maxHealth = 0;
        for(CardInput card : affectedRow) {
            if(card.getAttackDamage() > maxHealth) {
                maxHealth = card.getAttackDamage();
                selectedCard = card;
            }
        }

        affectedRow.remove(selectedCard);
    }
}