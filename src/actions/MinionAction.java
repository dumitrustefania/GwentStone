package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import abilities.minion_abilities.Cursed;
import abilities.minion_abilities.Disciple;
import abilities.minion_abilities.Miraj;
import abilities.minion_abilities.Ripper;
import players.Player;
import util.JsonOut;

import java.util.ArrayList;

/**
 * MinionAction class is used for performing specific actions
 * of the hero cards.
 */
public final class MinionAction extends Action {
    public MinionAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * Check the command name and execute the necessary steps accordingly.
     * Commands performed by minions can be one:
     *      - attack another minion
     *      - attack opponent's hero
     *      - use special ability
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
     * Execute the minion's attack on another minion.
     * First check whether the command is a valid one.
     * Then decrease attacked minion's life.
     */
    public void useAttack() {
        JsonOut out = new JsonOut();
        out.setCommand(Constants.USE_ATTACK);
        out.setCardAttacked(action.getCardAttacked());
        out.setCardAttacker(action.getCardAttacker());

        // determine attacker and attacked minions
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

        if (containsTanks() && !match.getTanks().contains(cardAttacked)) {
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        // perform attack
        int health = cardAttacked.getHealth();
        int damage = cardAttacker.getAttackDamage();
        cardAttacked.setHealth(health - damage);

        // check if attacked card is dead
        if (cardAttacked.getHealth() <= 0) {
            match.getFrozenCards().remove(cardAttacked);
            match.getTable().removeCard(cardAttacked);
        }

        // memorize that attacker attacked
        match.getAttackedThisTurn().add(cardAttacker);
    }

    /**
     * Execute the minion's attack on opponent's hero.
     * First check whether the command is a valid one.
     * Then decrease hero's life and check if game ended.
     */
    public void useAttackOnHero() {
        JsonOut out = new JsonOut();

        // determine attacker and attacked hero
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

        if (containsTanks()) {
            out.setCommand(Constants.USE_ATTACK_HERO);
            out.setCardAttacker(action.getCardAttacker());
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        // attack hero
        int health = hero.getHealth();
        int damage = cardAttacker.getAttackDamage();
        hero.setHealth(health - damage);

        // check if hero is dead and set the winner
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

        // memorize that attacker attacked
        match.getAttackedThisTurn().add(cardAttacker);
    }

    /**
     * Execute the minion's ability.
     * First check whether the command is a valid one.
     * Then perform the ability.
     */
    public void useCardAbility() {
        JsonOut out = new JsonOut();
        out.setCommand(Constants.CARD_USE_ABILITY);
        out.setCardAttacker(action.getCardAttacker());
        out.setCardAttacked(action.getCardAttacked());

        // determine attacker and attacked minions
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

        if (!cardAttacker.getName().equals(Constants.DISCIPLE)
                && containsTanks() && !match.getTanks().contains(cardAttacked)) {
            out.setError("Attacked card is not of type 'Tank'.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        // create specific objects based on the minion's name
        // and perform its ability
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

        // memorize that attacker attacked
        match.getAttackedThisTurn().add(cardAttacker);
    }

    /**
     * @param row - row to be checked for tanks
     * @return - true if there is at least one tank card on the input row
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
     * @return - true if input card is frozen
     */
    public boolean frozenCheck(final CardInput card) {
        return match.getFrozenCards().contains(card);
    }

    /**
     * @return - true if one of opponent's rows contains a tank
     */
    public boolean containsTanks() {
        ArrayList<CardInput> cardsOnRow1 = getRow(0, match.getOtherPlayer());
        ArrayList<CardInput> cardsOnRow2 = getRow(1, match.getOtherPlayer());
        return (tankExistsOnRow(cardsOnRow1) || tankExistsOnRow(cardsOnRow2));
    }

    /**
     * @param card
     * @param player
     * @return - true if input card belongs to input player
     * (it is placed on one of his rows on the table)
     */
    public boolean cardBelongsToPlayer(final CardInput card, final Player player) {
        ArrayList<CardInput> cardsOnRow1 = getRow(0, player);
        ArrayList<CardInput> cardsOnRow2 = getRow(1, player);

        return (cardsOnRow1.contains(card) || cardsOnRow2.contains(card));
    }

    /**
     * @param idx - index of wanted row, 0 = back, 1 = front
     * @param player
     * @return - row with index idx of input player
     */
    public ArrayList<CardInput> getRow(final int idx, final Player player) {
        int rowIdx = player.getRowsAssigned().get(idx);
        return match.getTable().getTable().get(rowIdx);
    }
}
