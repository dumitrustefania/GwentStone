package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JSONout {
    private String command;
    private Object output;

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
