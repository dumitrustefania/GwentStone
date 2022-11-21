package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Coordinates;

/**
 * JSONout class was created for easily appending the
 * necessary JSON values to the output.
 */
public class JsonOut {
    private String command;
    private Object output;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)
    private int playerIdx = -1;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)
    private int handIdx = -1;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)
    private int affectedRow = -1;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)
    private int x = -1;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int y = -1;
    private String gameEnded;
    private String error;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;

    /**
     * Appends the current JSONout object to the array.
     * @param output - the ArrayNode of JSON values
     */
    public void appendToArrayNode(final ArrayNode output) {
        ObjectMapper objectMapper = new ObjectMapper();
        // only include non-null values
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonNode node = objectMapper.valueToTree(this);
        output.add(node);
    }

    public final void setGameEnded(final String gameEnded) {
        this.gameEnded = gameEnded;
    }

    public final String getGameEnded() {
        return gameEnded;
    }

    public final int getHandIdx() {
        return handIdx;
    }

    public final void setHandIdx(final int handIdx) {
        this.handIdx = handIdx;
    }

    public final Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public final void setCardAttacker(final Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public final Coordinates getCardAttacked() {
        return cardAttacked;
    }

    public final void setCardAttacked(final Coordinates cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public final int getAffectedRow() {
        return affectedRow;
    }

    public final void setAffectedRow(final int affectedRow) {
        this.affectedRow = affectedRow;
    }

    public final int getPlayerIdx() {
        return playerIdx;
    }

    public final void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public final String getError() {
        return error;
    }

    public final void setError(final String error) {
        this.error = error;
    }

    public final String getCommand() {
        return command;
    }

    public final void setCommand(final String command) {
        this.command = command;
    }

    public final Object getOutput() {
        return output;
    }

    public final void setOutput(final Object output) {
        this.output = output;
    }

    public final void setX(final int x) {
        this.x = x;
    }

    public final void setY(final int y) {
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }
}
