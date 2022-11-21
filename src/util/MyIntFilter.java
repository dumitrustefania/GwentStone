package util;

/**
 * MyIntFilter class is created for being used with the command:
 * @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = MyIntFilter.class).
 * It acts like a filter, making sure that a field with a value equal
 * to -1 is not being shown into the JSON.
 */
public class MyIntFilter {
    /**
     * @param o - integer to be filtered
     * @return - true if filtering out (excluding) the field, false to include
     */
    @Override
    public final boolean equals(Object o) {
        // only ever called with int values
        Integer other = (Integer) o;
        return other == null || other == -1;
    }
}
