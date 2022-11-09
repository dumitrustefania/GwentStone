package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Coordinates;

public class JSONout {
    private String command;
    private Object output;

    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int playerIdx = -1;


    private String error;


    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int handIdx = -1;
    private Coordinates cardAttacker;
    private Coordinates cardAttacked;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class)

    private int affectedRow = -1;

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(int handIdx) {
        this.handIdx = handIdx;
    }

    public Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public Coordinates getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(Coordinates cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public int getAffectedRow() {
        return affectedRow;
    }

    public void setAffectedRow(int affectedRow) {
        this.affectedRow = affectedRow;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public JSONout(String command, Object output) {
        this.command = command;
        this.output = output;
    }

    public JSONout() {
    }

    public void appendToArrayNode(ArrayNode output) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper = JsonMapper.builder().
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        ObjectMapper objectMapper = JsonMapper.builder()
//                .configOverride(Integer.class)
//                .setInclude(JsonInclude.Value.empty()
//                        .withValueInclusion(JsonInclude.Include.CUSTOM)
//                        .withValueFilter(MyIntFilter.class)
//                ).build();
        
        JsonNode node = objectMapper.valueToTree(this);
        output.add(node);
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}
