package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.CardInput;
import common.Constants;
import play.abilities.minion_abilities.Cursed;
import play.abilities.minion_abilities.Disciple;
import play.abilities.minion_abilities.Miraj;
import play.abilities.minion_abilities.Ripper;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

public class MinionAction extends Action{
    public MinionAction(Action action) {
        super(action.action, action.game);
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();
        if(command.equals(Constants.USE_ATTACK))
            this.UseAttack();

        if(command.equals(Constants.USE_ATTACK_HERO))
            this.UseAttackOnHero();

        if(command.equals(Constants.CARD_USE_ABILITY))
            this.UseCardAbility();
    }

    public void UseAttack() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(Constants.USE_ATTACK);
        out.setCardAttacked(action.getCardAttacked());
        out.setCardAttacker(action.getCardAttacker());

        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = game.getTable().getCard(rowAttacker, colAttacker);

        int rowAttacked = action.getCardAttacked().getX();
        int colAttacked = action.getCardAttacked().getY();
        CardInput cardAttacked = game.getTable().getCard(rowAttacked, colAttacked);

        if(frozenCheck(cardAttacker)) {
            out.setError("Attacker card is frozen.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(attackedThisTurn(cardAttacker)) {
            out.setError("Attacker card has already attacked this turn.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(!cardBelongsToPlayer(cardAttacked, game.getOtherPlayer())) {
            out.setError("Attacked card does not belong to the enemy.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        //daca cartea atacata nu e tanc verific daca e vreun tanc
        if(containsTanks() && !game.getTanks().contains(cardAttacked)) {
                out.setError("Attacked card is not of type 'Tank'.");
                out.appendToArrayNode(game.getOutput());
                return;
        }

        //ataca pe bune
        int health = cardAttacked.getHealth();
        int damage = cardAttacker.getAttackDamage();
        cardAttacked.setHealth(health - damage);

        //check if dead
        if(cardAttacked.getHealth() <= 0) {
            game.getFrozenCards().remove(cardAttacked);
            game.getTable().removeCard(cardAttacked);
        }


        //memorize that attacker attacked
        game.getAttackedThisTurn().add(cardAttacker);
    }

    public void UseAttackOnHero() throws JsonProcessingException {
        JSONout out = new JSONout();

        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = game.getTable().getCard(rowAttacker, colAttacker);

        CardInput hero = game.getOtherPlayer().getHero();

        if(frozenCheck(cardAttacker)) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacker card is frozen.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(attackedThisTurn(cardAttacker)) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacker card has already attacked this turn.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        //verific daca e vreun tanc
        if(containsTanks()) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        //ataca pe bune
        int health = hero.getHealth();
        int damage = cardAttacker.getAttackDamage();
        hero.setHealth(health - damage);

        //check if dead
        if(hero.getHealth() <= 0) {
            if(game.getCurrentPlayer().getPlayerNum() == 1) {
                out.setGameEnded("Player one killed the enemy hero.");
                game.getPlayers()[1].setWins(game.getPlayers()[1].getWins() + 1);
            }
            else {
                out.setGameEnded("Player two killed the enemy hero.");
                game.getPlayers()[2].setWins(game.getPlayers()[2].getWins() + 1);
            }

            out.appendToArrayNode(game.getOutput());
            return;
        }

        //memorize that attacker attacked
        game.getAttackedThisTurn().add(cardAttacker);
    }

    public void UseCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(Constants.CARD_USE_ABILITY);
        out.setCardAttacker(action.getCardAttacker());
        out.setCardAttacked(action.getCardAttacked());

        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = game.getTable().getCard(rowAttacker, colAttacker);

        int rowAttacked = action.getCardAttacked().getX();
        int colAttacked = action.getCardAttacked().getY();
        CardInput cardAttacked = game.getTable().getCard(rowAttacked, colAttacked);

        if(frozenCheck(cardAttacker)) {
            out.setError("Attacker card is frozen.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(attackedThisTurn(cardAttacker)) {
            out.setError("Attacker card has already attacked this turn.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(cardAttacker.getName().equals(Constants.DISCIPLE) &&
            !cardBelongsToPlayer(cardAttacked, game.getCurrentPlayer())) {
            out.setError("Attacked card does not belong to the current player.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if((cardAttacker.getName().equals(Constants.RIPPER) ||
            cardAttacker.getName().equals(Constants.MIRAJ) ||
            cardAttacker.getName().equals(Constants.CURSED)) &&
                !cardBelongsToPlayer(cardAttacked, game.getOtherPlayer())) {
            out.setError("Attacked card does not belong to the enemy.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        System.out.println(game.getCurrentPlayer().getPlayerNum());
        //daca cartea atacata nu e tanc verific daca e vreun tanc
        if(!cardAttacker.getName().equals(Constants.DISCIPLE) &&
            containsTanks() && !game.getTanks().contains(cardAttacked)) {
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(game.getOutput());
            return;
        }
        System.out.println(cardAttacker.getName());
        //use the ability
        if(cardAttacker.getName().equals(Constants.RIPPER))
            new Ripper(cardAttacker, cardAttacked).useAbility();

        if(cardAttacker.getName().equals(Constants.MIRAJ))
            new Miraj(cardAttacker, cardAttacked).useAbility();

        if(cardAttacker.getName().equals(Constants.DISCIPLE))
            new Disciple(cardAttacker, cardAttacked).useAbility();

        if(cardAttacker.getName().equals(Constants.CURSED))
            new Cursed(cardAttacker, cardAttacked).useAbility();

        if(cardAttacked.getHealth() <= 0) {
            game.getTable().removeCard(cardAttacked);
            game.getFrozenCards().remove(cardAttacked);
        }

        //memorize that attacker attacked
        game.getAttackedThisTurn().add(cardAttacker);
    }

    public boolean TankExistsOnRow(ArrayList <CardInput> row) {
        for (CardInput card : row)
            if(game.getTanks().contains(card))
                return true;
        return false;
        }
    public boolean frozenCheck(CardInput card) {
        return game.getFrozenCards().contains(card);
    }

    public boolean containsTanks() {
        ArrayList<CardInput> cardsOnRow1 = getRow(0, game.getOtherPlayer());
        ArrayList<CardInput> cardsOnRow2 = getRow(1, game.getOtherPlayer());
        return (TankExistsOnRow(cardsOnRow1) || TankExistsOnRow(cardsOnRow2));
    }
    public boolean cardBelongsToPlayer(CardInput card, Player player) {
        ArrayList<CardInput> cardsOnRow1 = getRow(0, player);
        ArrayList<CardInput> cardsOnRow2 = getRow(1, player);

        return (cardsOnRow1.contains(card) || cardsOnRow2.contains(card));
    }

}
