package util;

public class MyIntFilter {
    // Return true if filtering out (excluding), false to include
    @Override
    public boolean equals(Object o) {
        // only ever called with String value
        Integer other = (Integer) o;
        if (other == null || other == -1) {
            return true;
        } else {
            return false;
        }
    }
}
