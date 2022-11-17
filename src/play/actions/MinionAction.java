package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.abilities.minion_abilities.Cursed;
import play.abilities.minion_abilities.Disciple;
import play.abilities.minion_abilities.Miraj;
import play.abilities.minion_abilities.Ripper;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class MinionAction extends Action {
    public MinionAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (command.equals(Constants.USE_ATTACK)) {
            this.useAttack();
        }

        if (command.equals(Constants.USE_ATTACK_HERO)) {
            this.useAttackOnHero();
        }

        if (command.equals(Constants.CARD_USE_ABILITY)) {
            this.useCardAbility();
        }
    }

    /**
     * @throws JsonProcessingException
     */
    public void useAttack() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(Constants.USE_ATTACK);
        out.setCardAttacked(action.getCardAttacked());
        out.setCardAttacker(action.getCardAttacker());

        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = match.getTable().getCard(rowAttacker, colAttacker);

        int rowAttacked = action.getCardAttacked().getX();
        int colAttacked = action.getCardAttacked().getY();
        CardInput cardAttacked = match.getTable().getCard(rowAttacked, colAttacked);

        if (frozenCheck(cardAttacker)) {
            out.setError("Attacker card is frozen.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (attackedThisTurn(cardAttacker)) {
            out.setError("Attacker card has already attacked this turn.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (!cardBelongsToPlayer(cardAttacked, match.getOtherPlayer())) {
            out.setError("Attacked card does not belong to the enemy.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        //daca cartea atacata nu e tanc verific daca e vreun tanc
        if (containsTanks() && !match.getTanks().contains(cardAttacked)) {
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        //ataca pe bune
        int health = cardAttacked.getHealth();
        int damage = cardAttacker.getAttackDamage();
        cardAttacked.setHealth(health - damage);

        //check if dead
        if (cardAttacked.getHealth() <= 0) {
            match.getFrozenCards().remove(cardAttacked);
            match.getTable().removeCard(cardAttacked);
        }

        //memorize that attacker attacked
        match.getAttackedThisTurn().add(cardAttacker);
    }

    /**
     * @throws JsonProcessingException
     */
    public void useAttackOnHero() throws JsonProcessingException {
        JSONout out = new JSONout();

        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = match.getTable().getCard(rowAttacker, colAttacker);

        CardInput hero = match.getOtherPlayer().getHero();

        if (frozenCheck(cardAttacker)) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacker card is frozen.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (attackedThisTurn(cardAttacker)) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacker card has already attacked this turn.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        //verific daca e vreun tanc
        if (containsTanks()) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        //ataca pe bune
        int health = hero.getHealth();
        int damage = cardAttacker.getAttackDamage();
        hero.setHealth(health - damage);

        //check if dead
        if (hero.getHealth() <= 0) {
            if (match.getCurrentPlayer().getPlayerNum() == 1) {
                out.setGameEnded("Player one killed the enemy hero.");
                match.getPlayers()[1].setWins(match.getPlayers()[1].getWins() + 1);
            } else {
                out.setGameEnded("Player two killed the enemy hero.");
                match.getPlayers()[2].setWins(match.getPlayers()[2].getWins() + 1);
            }

            out.appendToArrayNode(match.getOutput());
            return;
        }

        //memorize that attacker attacked
        match.getAttackedThisTurn().add(cardAttacker);
    }

    /**
     * @throws JsonProcessingException
     */
    public void useCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(Constants.CARD_USE_ABILITY);
        out.setCardAttacker(action.getCardAttacker());
        out.setCardAttacked(action.getCardAttacked());

        int rowAttacker = action.getCardAttacker().getX();
        int colAttacker = action.getCardAttacker().getY();
        CardInput cardAttacker = match.getTable().getCard(rowAttacker, colAttacker);

        int rowAttacked = action.getCardAttacked().getX();
        int colAttacked = action.getCardAttacked().getY();
        CardInput cardAttacked = match.getTable().getCard(rowAttacked, colAttacked);

        if (frozenCheck(cardAttacker)) {
            out.setError("Attacker card is frozen.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (attackedThisTurn(cardAttacker)) {
            out.setError("Attacker card has already attacked this turn.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (cardAttacker.getName().equals(Constants.DISCIPLE)
                && !cardBelongsToPlayer(cardAttacked, match.getCurrentPlayer())) {
            out.setError("Attacked card does not belong to the current player.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if ((cardAttacker.getName().equals(Constants.RIPPER)
                || cardAttacker.getName().equals(Constants.MIRAJ)
                || cardAttacker.getName().equals(Constants.CURSED))
                && !cardBelongsToPlayer(cardAttacked, match.getOtherPlayer())) {
            out.setError("Attacked card does not belong to the enemy.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        //daca cartea atacata nu e tanc verific daca e vreun tanc
        if (!cardAttacker.getName().equals(Constants.DISCIPLE)
                && containsTanks() && !match.getTanks().contains(cardAttacked)) {
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        //use the ability
        if (cardAttacker.getName().equals(Constants.RIPPER)) {
            new Ripper(cardAttacker, cardAttacked).useAbility();
        }

        if (cardAttacker.getName().equals(Constants.MIRAJ)) {
            new Miraj(cardAttacker, cardAttacked).useAbility();
        }

        if (cardAttacker.getName().equals(Constants.DISCIPLE)) {
            new Disciple(cardAttacker, cardAttacked).useAbility();
        }

        if (cardAttacker.getName().equals(Constants.CURSED)) {
            new Cursed(cardAttacker, cardAttacked).useAbility();
        }

        if (cardAttacked.getHealth() <= 0) {
            match.getTable().removeCard(cardAttacked);
            match.getFrozenCards().remove(cardAttacked);
        }

        //memorize that attacker attacked
        match.getAttackedThisTurn().add(cardAttacker);
    }

    /**
     * @param row
     * @return
     */
    public boolean tankExistsOnRow(final ArrayList<CardInput> row) {
        for (CardInput card : row) {
            if (match.getTanks().contains(card)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param card
     * @return
     */
    public boolean frozenCheck(final CardInput card) {
        return match.getFrozenCards().contains(card);
    }

    /**
     * @return
     */
    public boolean containsTanks() {
        ArrayList<CardInput> cardsOnRow1 = getRow(0, match.getOtherPlayer());
        ArrayList<CardInput> cardsOnRow2 = getRow(1, match.getOtherPlayer());
        return (tankExistsOnRow(cardsOnRow1) || tankExistsOnRow(cardsOnRow2));
    }

    /**
     * @param card
     * @param player
     * @return
     */
    public boolean cardBelongsToPlayer(final CardInput card, final Player player) {
        ArrayList<CardInput> cardsOnRow1 = getRow(0, player);
        ArrayList<CardInput> cardsOnRow2 = getRow(1, player);

        return (cardsOnRow1.contains(card) || cardsOnRow2.contains(card));
    }

    /**
     * @param idx
     * @param player
     * @return
     */
    public ArrayList<CardInput> getRow(final int idx, final Player player) {
        int rowIdx = player.getRowsAssigned().get(idx);
        return match.getTable().getTable().get(rowIdx);
    }
}
