package util;

/**
 *
 */
public class MyIntFilter {
    /**
     * @param o
     * @return
     */
    // Return true if filtering out (excluding), false to include
    @Override
    public boolean equals(Object o) {
        // only ever called with String value
        Integer other = (Integer) o;
        return other == null || other == -1;
    }
}
