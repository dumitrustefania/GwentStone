package players;

import util.Constants;
import fileio.CardInput;
import fileio.DecksInput;

import java.util.ArrayList;

/**
 * Class Player contains all the necessary data about a player.
 */
public class Player {
    private DecksInput decks;
    private ArrayList<CardInput> currentDeck;
    private ArrayList<CardInput> hand;
    private int mana;
    private final ArrayList<Integer> rowsAssigned = new ArrayList<Integer>();
    private CardInput hero;
    private int wins = 0;
    private int playerNum;

    /**
     * At the start of each round, add the first card from the deck
     * to the player's hand.
     */
    public void addCardInHandFromDeck() {
        if (currentDeck.isEmpty()) {
            return;
        }

        CardInput firstCardInDeck = currentDeck.get(0);
        hand.add(firstCardInDeck);
        currentDeck.remove(0);
    }

    /**
     * Set the current deck of the player by creating a deep copy
     * of the deck at the given index.
     * @param deckIdx - index of the deck to be used
     */
    public void setCurrentDeck(final int deckIdx) {
        ArrayList<CardInput> deck = decks.getDecks().get(deckIdx);

        // create a deep copy of the deck
        ArrayList<CardInput> deckCopy = new ArrayList<CardInput>();
        for (CardInput card : deck) {
            deckCopy.add(copyCard(card));
        }

        this.currentDeck = deckCopy;
    }

    /**
     * Copy every element of a card into a new one to avoid
     * data modification when changing health or damage fields.
     * @param card - card to be copied
     * @return - the new card, identical to the input card
     */
    public static CardInput copyCard(final CardInput card) {
        CardInput newCard = new CardInput();

        newCard.setAttackDamage(card.getAttackDamage());
        newCard.setHealth(card.getHealth());
        newCard.setColors(card.getColors());
        newCard.setName(card.getName());
        newCard.setDescription(card.getDescription());
        newCard.setMana(card.getMana());

        return newCard;
    }

    /**
     * Initialize player at the start of each match by clearing the previous
     * deck and hero pointers, setting its mana to 0 and creating a
     * new ArrayList for the hand.
     */
    public void initPlayer() {
        currentDeck = null;
        hand = new ArrayList<CardInput>();
        mana = 0;
        hero = null;
    }

    public Player(final int playerNum, final int row1, final int row2) {
        this.playerNum = playerNum;
        this.rowsAssigned.add(row1);
        this.rowsAssigned.add(row2);
    }

    public Player() {
    }

    public final ArrayList<Integer> getRowsAssigned() {
        return rowsAssigned;
    }

    public final int getWins() {
        return wins;
    }

    public final void setWins(final int wins) {
        this.wins = wins;
    }

    public final int getPlayerNum() {
        return playerNum;
    }

    public final DecksInput getDecks() {
        return decks;
    }

    public final void setDecks(final DecksInput decks) {
        this.decks = decks;
    }

    public final ArrayList<CardInput> getHand() {
        return hand;
    }

    public final int getMana() {
        return mana;
    }

    public final void setMana(final int mana) {
        this.mana = mana;
    }

    public final CardInput getHero() {
        return hero;
    }

    public final void setHero(final CardInput hero) {
        this.hero = hero;
        // when setting the hero, also set its health to 30
        this.hero.setHealth(Constants.INITIAL_HERO_HEALTH);
    }
    public final ArrayList<CardInput> getCurrentDeck() {
        return currentDeck;
    }
}
