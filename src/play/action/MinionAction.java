package play.action;

import fileio.ActionsInput;
import fileio.CardInput;
import play.Game;
import common.Constants;
import play.table.Table;

public class MinionAction extends Action{
    public MinionAction(ActionsInput action, Game game) {
        super(action, game);
    }

    public void performAction() {
        String command = action.getCommand();
        if(command.equals(Constants.USE_ATTACK)) {
            this.UseAttack(action, game);
        }

//        if(command.equals(Constants.USE_ATTACK_HERO)) {
//            UseHeroAttack(action, game);
//        }
//
//        if(command.equals(Constants.CARD_USE_ABILITY)) {
//            UseAbility(action, game);
//        }
    }

    public void UseAttack(ActionsInput action, Game game) {
        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = game.getTable().getCard(rowAttacker, colAttacker);

        int rowAttacked = action.getCardAttacked().getX();
        int colAttacked = action.getCardAttacked().getY();
        CardInput cardAttacked = game.getTable().getCard(rowAttacked, colAttacked);

        int health = cardAttacked.getHealth();
        int damage = cardAttacker.getAttackDamage();
        cardAttacked.setHealth(health - damage);
    }
//
//    public static void UseHeroAttack(ActionsInput action, Game game) {
//        int rowAttacker = action.getCardAttacker().getX();
//        int colAttacker = action.getCardAttacker().getY();
//        CardInput cardAttacker = game.getTable().getCard(rowAttacker, colAttacker);
//
//        CardInput hero = game.getOtherPlayer().getHero();
//        int health = hero.getHealth();
//        int damage = cardAttacker.getAttackDamage();
//        hero.setHealth(health - damage);
//    }
//
//    public void UseAbility(ActionsInput action, Game game) {
//        int rowAttacker = action.getCardAttacker().getX();
//        int colAttacker = action.getCardAttacker().getY();
//        CardInput cardAttacker = game.getTable().getCard(rowAttacker, colAttacker);
//
//        int rowAttacked = action.getCardAttacked().getX();
//        int colAttacked = action.getCardAttacked().getY();
//        CardInput cardAttacked = game.getTable().getCard(rowAttacked, colAttacked);
//
//        if(cardAttacker.getName().equals(Constants.RIPPER))
//            Ripper.UseAbility(cardAttacker, cardAttacked);
//
//
//    }
}
