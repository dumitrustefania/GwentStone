package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Constants {
    // game constants
    public static final int MAX_COLS = 5;
    public static final int MAX_ROWS = 4;

    public static final int NUM_PLAYERS = 2;
    public static final int MAX_MANA_ADDED = 10;
    public static final int INITIAL_HERO_HEALTH = 30;

    // minion names
    public static final String SENTINEL = "Sentinel";
    public static final String BERSERKER = "Berserker";
    public static final String GOLIATH = "Goliath";
    public static final String WARDEN = "Warden";
    public static final String MIRAJ = "Miraj";
    public static final String RIPPER = "The Ripper";
    public static final String DISCIPLE = "Disciple";
    public static final String CURSED = "The Cursed One";

    /* HashMap defining where each card has to be placed
    0 = front row
    1 = back row
    */
    public static final HashMap<String, Integer> ROW_TO_BE_PLACED_ON = new HashMap<>() {{
        put(SENTINEL, 1);
        put(BERSERKER, 1);
        put(CURSED, 1);
        put(DISCIPLE, 1);
        put(RIPPER, 0);
        put(MIRAJ, 0);
        put(GOLIATH, 0);
        put(WARDEN, 0);
    }};

    // environment card names
    public static final String FIRESTORM = "Firestorm";
    public static final String WINTERFELL = "Winterfell";
    public static final String HEARTHOUND = "Heart Hound";
    public static final ArrayList<String> ENV_CARDS = new ArrayList<String>(
            List.of(FIRESTORM, WINTERFELL, HEARTHOUND));

    // hero names
    public static final String LORD = "Lord Royce";
    public static final String EMPRESS = "Empress Thorina";
    public static final String KING = "King Mudface";
    public static final String GENERAL = "General Kocioraw";

    // commands
    public static final String END_TURN = "endPlayerTurn";
    public static final String PLACE_CARD = "placeCard";
    public static final ArrayList<String> PLAYER_ACTIONS = new ArrayList<String>(
            List.of(END_TURN, PLACE_CARD));
    public static final String USE_ATTACK = "cardUsesAttack";
    public static final String CARD_USE_ABILITY = "cardUsesAbility";
    public static final String USE_ATTACK_HERO = "useAttackHero";
    public static final ArrayList<String> MINION_ACTIONS = new ArrayList<String>(
            List.of(USE_ATTACK, CARD_USE_ABILITY, USE_ATTACK_HERO));
    public static final String HERO_USE_ABILITY = "useHeroAbility";
    public static final ArrayList<String> HERO_ACTIONS = new ArrayList<String>(
            List.of(HERO_USE_ABILITY));
    public static final String ENVIRONMENT_USE_ABILITY = "useEnvironmentCard";
    public static final ArrayList<String> ENV_ACTIONS = new ArrayList<String>(
            List.of(ENVIRONMENT_USE_ABILITY));
    public static final String GET_HAND = "getCardsInHand";
    public static final String GET_DECK = "getPlayerDeck";
    public static final String GET_TABLE = "getCardsOnTable";
    public static final String GET_TURN = "getPlayerTurn";
    public static final String GET_HERO = "getPlayerHero";
    public static final String GET_CARD = "getCardAtPosition";
    public static final String GET_MANA = "getPlayerMana";
    public static final String GET_ENV = "getEnvironmentCardsInHand";
    public static final String GET_FROZEN = "getFrozenCardsOnTable";
    public static final String GET_GAMES = "getTotalGamesPlayed";
    public static final String GET_WINS_ONE = "getPlayerOneWins";
    public static final String GET_WINS_TWO = "getPlayerTwoWins";
    public static final ArrayList<String> DEBUG_ACTIONS = new ArrayList<String>(
            List.of(GET_HAND, GET_DECK, GET_TABLE, GET_TURN, GET_HERO, GET_CARD,
                    GET_MANA, GET_ENV, GET_FROZEN, GET_GAMES, GET_WINS_ONE, GET_WINS_TWO));
}
