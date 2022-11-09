package play.players;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.DecksInput;
import fileio.GameInput;

import java.util.ArrayList;

public class Player {
    private DecksInput decks;
    private ArrayList<CardInput> currentDeck;
    private ArrayList<CardInput> hand = new ArrayList<CardInput>();
    private int mana;
    private CardInput hero;

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    private int playerNum;

    public Player(int playerNum) {
        this.playerNum = playerNum;
    }

    public Player() {
    }

    public void addCardInHandFromDeck() {
        System.out.println("THE CUURENT DECK IS:" + currentDeck);
        CardInput firstCardInDeck = currentDeck.get(0);
        hand.add(firstCardInDeck);
        currentDeck.remove(0);
    }

    public ArrayList<CardInput> getHand() {
        return hand;
    }

    public void setHand(ArrayList<CardInput> hand) {
        this.hand = hand;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public CardInput getHero() {
        return hero;
    }

    public void setHero(CardInput hero) {
        this.hero = hero;
    }

    public ArrayList<CardInput> getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(ArrayList<CardInput> currentDeck) {
        this.currentDeck = currentDeck;
    }
    public void setCurrentDeck(int deckIdx) {
        ArrayList<CardInput> deck = decks.getDecks().get(deckIdx);

        // Deep copy
        ArrayList<CardInput> deckCopy = new ArrayList<CardInput>();
        for(CardInput card : deck) {
            deckCopy.add(card);
        }

        this.currentDeck = deckCopy;
    }

    public DecksInput getDecks() {
        return decks;
    }

    public void setDecks(DecksInput decks) {
        this.decks = decks;
    }
}
