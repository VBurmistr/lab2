package nc.apps.entities;

import java.util.Arrays;
import java.util.Optional;

public enum Ordering {
    ASC(1), DESC(2);
    private final int value;
    Ordering(int value) {
        this.value = value;
    }

    public static Optional<Ordering> valueOf(int value) {
        return Arrays.stream(values())
                .filter(ordering -> ordering.value == value)
                .findFirst();
    }
}
