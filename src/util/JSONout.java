package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Coordinates;

/**
 *
 */
public class JSONout {
    private String command;
    private Object output;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int playerIdx = -1;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int handIdx = -1;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int affectedRow = -1;
    private String gameEnded;
    private String error;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;

    public JSONout(final String command, final Object output) {
        this.command = command;
        this.output = output;
    }
    public JSONout() {
    }

    /**
     * @param output
     */
    public void appendToArrayNode(final ArrayNode output) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonNode node = objectMapper.valueToTree(this);
        output.add(node);
    }

    public String getGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(final String gameEnded) {
        this.gameEnded = gameEnded;
    }

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(final int handIdx) {
        this.handIdx = handIdx;
    }

    public Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(final Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public Coordinates getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(final Coordinates cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public int getAffectedRow() {
        return affectedRow;
    }

    public void setAffectedRow(final int affectedRow) {
        this.affectedRow = affectedRow;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(final String command) {
        this.command = command;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(final Object output) {
        this.output = output;
    }
}
